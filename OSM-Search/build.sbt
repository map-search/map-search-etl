name := "OSM-Search"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.0" % Provided

libraryDependencies +="org.elasticsearch" % "elasticsearch-hadoop" % "6.5.1"
libraryDependencies +="org.elasticsearch.client" % "elasticsearch-rest-high-level-client" % "6.5.1"


libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.0" 

resolvers += Resolver.mavenLocal