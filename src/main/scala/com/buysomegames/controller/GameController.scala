package com.buysomegames.controller

import com.buysomegames.controller.response.GamesResponse
import com.buysomegames.service.GameService
import com.google.inject.Inject
import com.twitter.bijection.twitter_util.UtilBijections._
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.http.response.ResponseBuilder
import com.twitter.util.{Future => TwitterFuture}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future => ScalaFuture}

class GameController @Inject()(
                                gameService: GameService,
                                response: ResponseBuilder
                              ) extends Controller {
  get("/games") { request: Request =>
    val games: ScalaFuture[GamesResponse] = gameService.handleFindAllGames
    twitter2ScalaFuture[GamesResponse].invert(games)
  }
}
