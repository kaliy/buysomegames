package com.buysomegames.controller

import com.buysomegames.service.GameService
import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.http.response.ResponseBuilder

class GameController @Inject()(
                                gameService: GameService,
                                response: ResponseBuilder
                              ) extends Controller {
  get("/games") { request: Request =>
    gameService.handleFindAllGames
  }
}
