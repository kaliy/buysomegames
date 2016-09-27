package com.buysomegames.service

import com.buysomegames.controller.response.GamesResponse
import com.buysomegames.repository.GameRepository
import com.google.inject.Inject

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class GameService @Inject()(gameRepository: GameRepository) {
  def handleFindAllGames: Future[GamesResponse] = {
    gameRepository.findAllGames.map(games => new GamesResponse(games))
  }
}
