package com.github.tototoshi.play.json4s.ws.jackson

import com.github.tototoshi.play.json4s.ws.core.{Json4sBodyReadables => CoreJson4sBodyReadables}
import org.json4s.JsonAST.JValue
import org.json4s.JsonMethods

trait Json4sBodyReadables extends CoreJson4sBodyReadables[JValue] {

  override protected val jsonMethods: JsonMethods[JValue] = org.json4s.jackson.JsonMethods

}

object Json4sBodyReadables extends Json4sBodyReadables
