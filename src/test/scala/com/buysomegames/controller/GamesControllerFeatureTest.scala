package com.buysomegames.controller

import java.util.concurrent.TimeUnit

import com.buysomegames.kernel.BuysomegamesServer
import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest
import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.bson.collection.immutable.Document

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.Source

class GamesControllerFeatureTest extends FeatureTest {
  override protected def server: EmbeddedHttpServer = new EmbeddedHttpServer(twitterServer = new BuysomegamesServer)

  "/games endpoint" should {
    "respond with information about all games" in {
      server.httpGet(
        path = "/games",
        andExpect = Status.Ok,
        withJsonBody = Source.fromInputStream(getClass.getResourceAsStream("/controller/games/games.json")).getLines.mkString
      )
    }
  }

  override protected def beforeEach(): Unit = {
    val collection = injector.instance[MongoDatabase].getCollection("games")
    val lines = Source.fromInputStream(getClass.getResourceAsStream("/data/games.json")).getLines
    Await.result(collection.drop().head(), Duration(10, TimeUnit.SECONDS))
    Await.result(collection.insertMany(lines.map(json => Document(json)).toSeq).toFuture(), Duration(10, TimeUnit.SECONDS))
  }
}
