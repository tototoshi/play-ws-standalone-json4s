package com.github.tototoshi.play.json4s.ws.native

import com.github.tototoshi.play.json4s.ws.core.{Json4sBodyWritables => CoreJson4sBodyWritables}
import org.json4s.JsonMethods
import org.json4s.native.Document

trait Json4sBodyWritables extends CoreJson4sBodyWritables[Document] {

  protected val jsonMethods: JsonMethods[Document] = org.json4s.native.JsonMethods

}

object Json4sBodyWritables extends Json4sBodyWritables
