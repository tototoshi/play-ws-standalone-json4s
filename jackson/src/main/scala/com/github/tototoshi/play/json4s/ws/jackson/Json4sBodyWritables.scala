package com.github.tototoshi.play.json4s.ws.jackson

import org.json4s.{JValue, JsonMethods}
import com.github.tototoshi.play.json4s.ws.core.{Json4sBodyWritables => CoreJson4sBodyWritables}

trait Json4sBodyWritables extends CoreJson4sBodyWritables[JValue] {

  protected val jsonMethods: JsonMethods[JValue] = org.json4s.jackson.JsonMethods

}

object Json4sBodyWritables extends Json4sBodyWritables
