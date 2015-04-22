name := "log_analysis"

version := "2.0"

scalaVersion := "2.11.6"

libraryDependencies += "com.netaporter" %% "scala-uri" % "0.4.6"
libraryDependencies += "log4j" % "log4j" % "1.2.14"

val finagleVersion = "6.24.0"

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-http" % finagleVersion,
  "com.twitter" %% "finagle-stats" % finagleVersion
)

assemblyJarName in assembly := "stfile_cleaner.jar"
test in assembly := {}
mainClass in assembly := Some("com.wangjf.FileCleaner")