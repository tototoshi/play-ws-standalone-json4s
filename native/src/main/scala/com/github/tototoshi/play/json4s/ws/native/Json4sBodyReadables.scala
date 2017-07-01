package com.github.tototoshi.play.json4s.ws.native

import com.github.tototoshi.play.json4s.ws.core.{Json4sBodyReadables => CoreJson4sBodyReadables}
import org.json4s.JsonMethods
import org.json4s.native.Document

trait Json4sBodyReadables extends CoreJson4sBodyReadables[Document] {

  override protected val jsonMethods: JsonMethods[Document] = org.json4s.native.JsonMethods

}

object Json4sBodyReadables extends Json4sBodyReadables
