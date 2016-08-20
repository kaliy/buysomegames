package com.buysomegames.kernel

import com.buysomegames.controller.GameController
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter

object BuysomegamesApp extends BuysomegamesServer

class BuysomegamesServer extends HttpServer {
  override protected def defaultFinatraHttpPort: String = ":10666"

  override def defaultHttpPort: Int = 11666

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .add[GameController]
  }
}