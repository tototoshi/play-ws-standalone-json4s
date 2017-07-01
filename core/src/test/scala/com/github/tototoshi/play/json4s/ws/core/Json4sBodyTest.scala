package com.github.tototoshi.play.json4s.ws.core

import org.json4s.{DefaultFormats, Extraction, JValue, JsonMethods}
import org.scalatest.{FunSuite, Matchers}
import play.api.libs.json.JsValue
import play.api.libs.ws.{BodyReadable, BodyWritable}
import play.api.mvc.{DefaultActionBuilder, PlayBodyParsers, Request}
import play.core.server.Server

abstract class Json4sBodyTest[T](
    methods: JsonMethods[T]
  )(implicit
    bodyReadable: BodyReadable[JValue],
    bodyWritable: BodyWritable[JValue])
  extends FunSuite
  with Matchers
  with WithWsClient
  with WithActorSystem {

  test("can send and receive Json4s request") {

    import play.api.mvc.Results._

    import scala.concurrent._
    import scala.concurrent.duration._
    import scala.language.postfixOps

    implicit val formats = DefaultFormats

    Server.withRouter() {
      case _ =>
        val defaultAction = DefaultActionBuilder(PlayBodyParsers().default)
        defaultAction(PlayBodyParsers().json) { request: Request[JsValue] =>
          Ok(request.body)
        }
    } { implicit port =>
      val user = User(1, "たかはし")
      val json = Extraction.decompose(user)
      val res = Await.result(
        ws.url("http://localhost:" + port)
          .post(json)
          .map(_.body[JValue]),
        5 seconds
      )
      res.extract[User] should be(user)
    }

  }

}
