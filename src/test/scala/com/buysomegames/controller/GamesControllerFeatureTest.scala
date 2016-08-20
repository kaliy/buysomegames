package com.buysomegames.controller

import com.buysomegames.kernel.BuysomegamesServer
import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

class GamesControllerFeatureTest extends FeatureTest {
  override protected def server: EmbeddedHttpServer = new EmbeddedHttpServer(twitterServer = new BuysomegamesServer)

  "/games endpoint" should {
    "respond with hello world" in {
      server.httpGet(
        path = "/games",
        andExpect = Status.Ok,
        withJsonBody = """{"hello":"world"}"""
      )
    }
  }
}
