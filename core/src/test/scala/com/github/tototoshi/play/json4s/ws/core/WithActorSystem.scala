package com.github.tototoshi.play.json4s.ws.core

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}
import org.scalatest._
import play.api.libs.ws.StandaloneWSClient
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration.Duration

trait HasActorSystem {
  implicit var actorSystem: ActorSystem
  implicit var actorMaterializer: Materializer
}

trait WithActorSystem extends BeforeAndAfterEach with HasActorSystem { self: TestSuite =>
  implicit var actorSystem: ActorSystem = _
  implicit var actorMaterializer: Materializer = _
  implicit var executionContext: ExecutionContext = _

  override protected def beforeEach(): Unit = {
    actorSystem = ActorSystem()
    executionContext = actorSystem.dispatcher
    actorMaterializer = ActorMaterializer()
    super.beforeEach()
  }

  override protected def afterEach(): Unit = {
    super.afterEach()
    Await.result(actorSystem.terminate(), Duration("30s"))
  }

}

trait WithWsClient extends BeforeAndAfterEach { self: TestSuite with HasActorSystem =>

  protected var ws: StandaloneWSClient = _

  override protected def beforeEach(): Unit = {
    ws = StandaloneAhcWSClient()
    super.beforeEach()
  }

  override protected def afterEach(): Unit = {
    super.afterEach()
    ws.close()
  }

}
