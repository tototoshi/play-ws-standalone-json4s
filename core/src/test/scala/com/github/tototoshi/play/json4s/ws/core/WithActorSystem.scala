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

trait WithActorSystem extends TestSuiteMixin with HasActorSystem { self: TestSuite =>
  implicit var actorSystem: ActorSystem = _
  implicit var actorMaterializer: Materializer = _
  implicit var executionContext: ExecutionContext = _

  abstract override def withFixture(test: NoArgTest): Outcome = {
    actorSystem = ActorSystem()
    executionContext = actorSystem.dispatcher
    actorMaterializer = ActorMaterializer()
    try {
      super.withFixture(test)
    } finally {
      Await.result(actorSystem.terminate(), Duration("30s"))
    }
  }
}

trait WithWsClient extends TestSuiteMixin { self: TestSuite with HasActorSystem =>

  protected var ws: StandaloneWSClient = _

  abstract override def withFixture(test: NoArgTest): Outcome = {
    ws = StandaloneAhcWSClient()
    try {
      super.withFixture(test)
    } finally {
      ws.close()
    }
  }

}
