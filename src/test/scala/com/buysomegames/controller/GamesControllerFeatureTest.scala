package com.buysomegames.controller

import com.buysomegames.kernel.BuysomegamesServer
import com.buysomegames.test.FreshDatabase
import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

import scala.io.Source

class GamesControllerFeatureTest extends FeatureTest with FreshDatabase {
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
}