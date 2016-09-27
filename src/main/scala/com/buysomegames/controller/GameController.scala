package com.buysomegames.controller

import com.buysomegames.model.Game
import com.buysomegames.repository.GameRepository
import com.google.inject.Inject
import com.twitter.bijection.twitter_util.UtilBijections._
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.http.response.ResponseBuilder
import com.twitter.util.{Future => TwitterFuture}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future => ScalaFuture}

class GameController @Inject()(
                                gameRepository: GameRepository,
                                response: ResponseBuilder
                              ) extends Controller {
  get("/games") { request: Request =>
    val games: ScalaFuture[Map[String, Iterable[Game]]] = gameRepository.findAllGames.map(games => Map("games" -> games))
    twitter2ScalaFuture[Map[String, Iterable[Game]]].invert(games)
  }

  class GamesResponse(val games: Iterable[Game])

}
