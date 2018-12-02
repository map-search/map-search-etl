import com.clearspring.analytics.util.ListNode2
import org.apache.http.HttpHost
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.StructType
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest
import org.elasticsearch.client.{RestClient, RestHighLevelClient}
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.xcontent.XContentType
import org.elasticsearch.spark.sql._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.elasticsearch.spark.rdd.EsSpark

import scala.util.Try

object SparkJob {

  def toString(payload: Array[Byte]) = new String(payload)

  val getTagFromStruct: UserDefinedFunction = udf((schema: GenericRowWithSchema) => Try(getTagFromStruct(schema)).toOption)

  def getTagFromStruct(schema: GenericRowWithSchema): String = {
    val key = toString(schema.get(0).asInstanceOf[Array[Byte]])
    val value = toString(schema.get(1).asInstanceOf[Array[Byte]])

    if (key.equals("name")) value else ""
  }

  def main(args: Array[String]): Unit = {

    case class Node()
    case class Way()
    case class Relation()
    val spark = SparkSession.builder.appName("Spark Job").master("local[*]") getOrCreate()
    import spark.implicits._
    implicit val sparkImplicit: SparkSession = spark
    val osmNodeDF = spark.sqlContext.read.parquet("hdfs://map-search-spark:9000/osm-data/Seoul.osm.pbf.node.parquet").toDF()
    osmNodeDF.createOrReplaceTempView("node")

    val node = osmNodeDF.filter(size($"tags") =!= 0)
    val node2 = node.select($"id", $"latitude", $"longitude", explode($"tags").as("tag"))
      .withColumn(s"location", getTagFromStruct($"tag"))
      .filter($"location" =!= "")
      .withColumn("location_tokens", split($"location", "\\s"))
      .select("location", "latitude", "longitude", "location_tokens")

    node2.printSchema()
    node2.show(500, false)
    val client = new RestHighLevelClient(RestClient.builder(new HttpHost("106.10.33.83", 9200, "http")))

    val request = new CreateIndexRequest("osm_index")
    //request.settings(Settings.builder().put("analysis"))
    .mapping(getMapping)

    node2.toDF().saveToEs("osm-index/_doc",Map("es.nodes" -> "http://106.10.33.83:9200"))

    client.close()

  }
  def getSetting: String =
    s"""
       |  "index": {
       |         "number_of_shards": 1,
       |         "number_of_replicas": 1
       |   },
       |   "analysis": {
       |     "analyzer": {
       |       "analyzer-name": {
       |             "type": "custom",
       |             "tokenizer": "keyword",
       |             "filter": "lowercase"
       |       }
       |     }
       |   }
     """.stripMargin
  def getMapping: String =
    s"""
       |"_doc": {
       |      "properties": {
       |        "location": { "type": text  },
       |        "latitude": { "type": text },
       |        "longitude":{ "type": text,
       |        "location_tokens":{"type":text,
       |        "created":  {
       |          "type":   "date",
       |          "format": "strict_date_optional_time||epoch_millis"
       |        }
       |      }
       |    }

    """

}
