package com.buysomegames.controller

import com.buysomegames.kernel.BuysomegamesServer
import com.buysomegames.test.FreshDatabase
import com.buysomegames.test.TestUtilities.readResource
import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

class GameEditionControllerFeatureTest extends FeatureTest with FreshDatabase {
  override protected def server: EmbeddedHttpServer = new EmbeddedHttpServer(new BuysomegamesServer)

  "/game_editions endpoint" should {
    "respond with information about all available game editions" in {
      server.httpGet(
        path = "/game_editions",
        andExpect = Status.Ok,
        withJsonBody = readResource("/controller/games/games.json")
      )
    }
  }
}