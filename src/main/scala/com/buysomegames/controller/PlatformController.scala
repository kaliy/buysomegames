package com.buysomegames.controller

import com.buysomegames.controller.response.PlatformsResponse
import com.buysomegames.service.PlatformService
import com.google.inject.Inject
import com.twitter.bijection.twitter_util.UtilBijections.twitter2ScalaFuture
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.http.response.ResponseBuilder

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future => ScalaFuture}

class PlatformController @Inject()(
                                    platformService: PlatformService,
                                    response: ResponseBuilder
                                  ) extends Controller {
  get("/platforms") { request: Request =>
    val platforms: ScalaFuture[PlatformsResponse] = platformService.handleFindAllPlatforms
    twitter2ScalaFuture[PlatformsResponse].invert(platforms)
  }
}
