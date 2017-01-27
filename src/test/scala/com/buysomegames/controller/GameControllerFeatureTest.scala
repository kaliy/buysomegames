package com.buysomegames.controller

import com.buysomegames.kernel.BuysomegamesServer
import com.buysomegames.test.FreshDatabase
import com.buysomegames.test.TestUtilities.readResource
import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

class GameControllerFeatureTest extends FeatureTest with FreshDatabase {
  override protected def server: EmbeddedHttpServer = new EmbeddedHttpServer(new BuysomegamesServer)

  "/games endpoint" should {
    "respond with information about all games" in {
      server.httpGet(
        path = "/games",
        andExpect = Status.Ok,
        withJsonBody = readResource("/controller/games/games.json")
      )
    }
  }
}