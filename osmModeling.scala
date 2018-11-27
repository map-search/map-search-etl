// convert osm.pbf data to parquet 
//참고 : https://wiki.openstreetmap.org/wiki/Osm-parquetizer

// hdfs에 osm data parquet format으로 적재 완료
val seoulNodeDF = spark.sqlContext.read.parquet("hdfs://localhost:9000/OsmData/Seoul.osm.pbf.node.parquet")
val seoulRelationDF = spark.sqlContext.read.parquet("hdfs://localhost:9000/OsmData/Seoul.osm.pbf.relation.parquet")
val seoulWayDF = spark.sqlContext.read.parquet("hdfs://localhost:9000/OsmData/Seoul.osm.pbf.way.parquet")

val nodeDF = spark.sqlContext.read.parquet("hdfs://localhost:9000/OsmData/south-korea-latest.osm.pbf.node.parquet")

//schema 확인하기
seoulNodeDF.printSchema()

//data 출력
seoulNodeDF.show(100,False)
