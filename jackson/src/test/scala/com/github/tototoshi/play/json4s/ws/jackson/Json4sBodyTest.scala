package com.github.tototoshi.play.json4s.ws.jackson

import com.github.tototoshi.play.json4s.ws.core.{Json4sBodyTest => CoreJson4sBodyTest}
import com.github.tototoshi.play.json4s.ws.jackson.Json4sBodyReadables._
import com.github.tototoshi.play.json4s.ws.jackson.Json4sBodyWritables._

class Json4sBodyTest extends CoreJson4sBodyTest(org.json4s.jackson.JsonMethods)
