# play-ws-standalone-json4s

[![Scala CI](https://github.com/tototoshi/play-ws-standalone-json4s/actions/workflows/scala.yml/badge.svg)](https://github.com/tototoshi/play-ws-standalone-json4s/actions/workflows/scala.yml)

BodyReadable/BodyWritable for Json4s


## Install

```scala
libraryDependencies += "com.github.tototoshi" %% "play-ws-standalone-json4s-jackson" % "0.3.1"
```

or

```scala
libraryDependencies += "com.github.tototoshi" %% "play-ws-standalone-json4s-native" % "0.3.1"
```

## Usage


```scala
import com.github.tototoshi.play.json4s.ws.jackson.Json4sBodyReadables._
import com.github.tototoshi.play.json4s.ws.jackson.Json4sBodyWritables._
```

or

```scala
import com.github.tototoshi.play.json4s.ws.native.Json4sBodyReadables._
import com.github.tototoshi.play.json4s.ws.native.Json4sBodyWritables._
```

## License

[Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
