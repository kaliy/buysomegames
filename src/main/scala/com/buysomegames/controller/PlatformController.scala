package com.buysomegames.controller

import com.buysomegames.service.PlatformService
import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.http.response.ResponseBuilder

import scala.concurrent.{Future => ScalaFuture}

class PlatformController @Inject()(
                                    platformService: PlatformService,
                                    response: ResponseBuilder
                                  ) extends Controller {
  get("/platforms") { request: Request =>
    platformService.handleFindAllPlatforms
  }
}
