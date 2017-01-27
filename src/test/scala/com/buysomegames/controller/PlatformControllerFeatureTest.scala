package com.buysomegames.controller

import com.buysomegames.kernel.BuysomegamesServer
import com.buysomegames.test.FreshDatabase
import com.buysomegames.test.TestUtilities.readResource
import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

class PlatformControllerFeatureTest extends FeatureTest with FreshDatabase {
  override protected def server: EmbeddedHttpServer = new EmbeddedHttpServer(new BuysomegamesServer)
  "/platforms endpoint" should {
    "respond with information about all platforms" in {
      server.httpGet(
        path = "/platforms",
        andExpect = Status.Ok,
        withJsonBody = readResource("/controller/platforms/platforms-response.json")
      )
    }
  }
}
