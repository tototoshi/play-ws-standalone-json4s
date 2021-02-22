val _version = "0.3.1-SNAPSHOT"

val playVersion = "2.7.9"
val playWsVersion = "2.0.1"
val json4sVersion = "3.6.5"
val scalatestVersion = "3.0.6"
val guiceVersion = "4.2.2"

val baseSettings = Seq(
  organization := "com.github.tototoshi",
  version := _version,
  scalaVersion := "2.12.8",
  crossScalaVersions ++= Seq("2.11.12", "2.12.8"),
  scalacOptions ++= Seq("-deprecation", "-feature"),
  libraryDependencies ++= Seq(
    "com.typesafe.play" %% "play-ahc-ws-standalone" % playWsVersion,
    "com.typesafe.play" %% "play" % playVersion % "test",
    "com.typesafe.play" %% "play-server" % playVersion % "test",
    "com.typesafe.play" %% "play-netty-server" % playVersion % "test",
    "com.google.inject" % "guice" % guiceVersion % "test",
    "org.scalatest" %% "scalatest" % scalatestVersion % "test"
  )
)

val baseTestSettings = Seq(
  fork in Test := true,
  javaOptions in Test ++= Seq("-Dplay.server.provider=play.core.server.NettyServerProvider")
)

val publishSettings = Seq(
  publishMavenStyle := true,
  publishTo := _publishTo(version.value),
  publishArtifact in Test := false,
  pomExtra := _pomExtra
)

val nonPublishSettings = Seq(
  skip in publish := true
)

lazy val `play-ws-standalone-json4s-core` = project
  .in(file("core"))
  .settings(baseSettings)
  .settings(publishSettings)
  .settings(
    name := """play-ws-standalone-json4s-core""",
    libraryDependencies ++= Seq(
      "org.json4s" %% "json4s-core" % json4sVersion
    )
  )

lazy val `play-ws-standalone-json4s-native` = project
  .in(file("native"))
  .settings(baseSettings)
  .settings(baseTestSettings)
  .settings(publishSettings)
  .settings(
    name := """play-ws-standalone-json4s-native""",
    libraryDependencies ++= Seq(
      "org.json4s" %% "json4s-native" % json4sVersion
    )
  )
  .dependsOn(`play-ws-standalone-json4s-core` % "compile->compile;test->test")

lazy val `play-ws-standalone-json4s-jackson` = project
  .in(file("jackson"))
  .settings(baseSettings)
  .settings(baseTestSettings)
  .settings(publishSettings)
  .settings(
    name := """play-ws-standalone-json4s-jackson""",
    libraryDependencies ++= Seq(
      "org.json4s" %% "json4s-jackson" % json4sVersion
    )
  )
  .dependsOn(`play-ws-standalone-json4s-core` % "compile->compile;test->test")

lazy val `play-ws-standalone-json4s` = project
  .in(file("."))
  .settings(baseSettings)
  .settings(nonPublishSettings)
  .aggregate(
    `play-ws-standalone-json4s-core`,
    `play-ws-standalone-json4s-native`,
    `play-ws-standalone-json4s-jackson`
  )

def _publishTo(v: String) = {
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

val _pomExtra =
  <url>https://github.com/tototoshi/play-ws-standalone-json4s</url>
  <licenses>
    <license>
      <name>Apache License, Version 2.0</name>
      <url>https://www.apache.org/licenses/LICENSE-2.0.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:tototoshi/play-ws-standalone-json4s.git</url>
    <connection>scm:git:git@github.com:tototoshi/play-ws-standalone-json4s.git</connection>
  </scm>
  <developers>
    <developer>
      <id>tototoshi</id>
      <name>Toshiyuki Takahashi</name>
      <url>https://tototoshi.github.io</url>
    </developer>
  </developers>
