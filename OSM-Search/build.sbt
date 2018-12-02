name := "OSM-Search"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.0" % Provided

libraryDependencies +="org.elasticsearch" % "elasticsearch-hadoop" % "6.5.1"
libraryDependencies +="org.elasticsearch.client" % "elasticsearch-rest-high-level-client" % "6.5.1"


libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.0" 

resolvers += Resolver.bintrayIvyRepo("com.eed3si9n", "sbt-plugins")


assemblyMergeStrategy in assembly := {
  case PathList("org","aopalliance", xs @ _*) => MergeStrategy.last
  case PathList("javax", "inject", xs @ _*) => MergeStrategy.last
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
  case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case PathList("com", "google", xs @ _*) => MergeStrategy.last
  case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
  case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
  case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
  case PathList("git.properties", xs @ _*) => MergeStrategy.discard
  case "about.html" => MergeStrategy.rename
  case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
  case "META-INF/mailcap" => MergeStrategy.last
  case "META-INF/mimetypes.default" => MergeStrategy.last
  case "plugin.properties" => MergeStrategy.last
  case "log4j.properties" => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
