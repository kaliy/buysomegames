package com.buysomegames.controller

import com.buysomegames.model.Game
import com.buysomegames.repository.GameRepository
import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class GameController @Inject()(
                                gameRepository: GameRepository
                              ) extends Controller {
  get("/games") { request: Request =>
    new GamesResponse(games = gameRepository.findAllGames)
  }

  class GamesResponse(val games: Iterable[Game])
}
