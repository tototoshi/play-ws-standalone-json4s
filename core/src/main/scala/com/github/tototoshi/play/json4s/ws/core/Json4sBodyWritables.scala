package com.github.tototoshi.play.json4s.ws.core

import akka.util.ByteString
import org.json4s.{JValue, JsonMethods}
import play.api.libs.ws.{BodyWritable, InMemoryBody}

trait Json4sBodyWritables[T] {

  protected val jsonMethods: JsonMethods[T]

  import jsonMethods._

  implicit val writeableOf_JsValue: BodyWritable[JValue] = {
    BodyWritable(a => InMemoryBody(ByteString.fromString(compact(render(a)))), "application/json")
  }

}
