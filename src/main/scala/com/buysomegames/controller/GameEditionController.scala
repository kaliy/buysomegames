package com.buysomegames.controller

import com.buysomegames.service.GameEditionService
import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.http.response.ResponseBuilder

class GameEditionController @Inject()(
                                        gameEditionService: GameEditionService,
                                        response: ResponseBuilder
                                      ) extends Controller {
  get("/game_editions") { request: Request =>
    gameEditionService.handleFindAllGameEditions
  }
}
