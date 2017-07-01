val playVersion = "2.6.0"
val playWsVersion = "1.0.0"
val json4sVersion = "3.5.2"
val scalatestVersion = "3.0.1"
val guiceVersion = "4.1.0"

val baseSettings = Seq(
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.2",
  crossScalaVersions ++= Seq("2.11.8", "2.12.2"),
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

lazy val `play-ws-standalone-json4s-core` = project
  .in(file("core"))
  .settings(baseSettings)
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
  .aggregate(
    `play-ws-standalone-json4s-core`,
    `play-ws-standalone-json4s-native`,
    `play-ws-standalone-json4s-jackson`
  )
