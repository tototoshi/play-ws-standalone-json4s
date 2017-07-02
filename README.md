# play-ws-standalone-json4s

[![Build Status](https://travis-ci.org/tototoshi/play-ws-standalone-json4s.png)](https://travis-ci.org/tototoshi/play-ws-standalone-json4s)


BodyReadable/BodyWritable for Json4s


## Install

```scala
libraryDependencies += "com.typesafe.play" %% "play-ws-standalone-json4s-jackson" % "0.1.0"
```

or

```scala
libraryDependencies += "com.typesafe.play" %% "play-ws-standalone-json4s-native" % "0.1.0"
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

