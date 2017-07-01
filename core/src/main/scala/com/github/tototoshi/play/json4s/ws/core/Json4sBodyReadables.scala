package com.github.tototoshi.play.json4s.ws.core

import org.json4s.{JValue, JsonMethods}
import play.api.libs.ws.BodyReadable

trait Json4sBodyReadables[T] {

  protected val jsonMethods: JsonMethods[T]

  import jsonMethods._

  implicit val readableAsJson: BodyReadable[JValue] = BodyReadable { response =>
    parse(response.bodyAsBytes.iterator.asInputStream)
  }

}