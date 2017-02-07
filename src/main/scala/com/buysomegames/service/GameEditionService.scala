package com.buysomegames.service

import com.buysomegames.controller.response.GameEditionsResponse
import com.buysomegames.repository.GameEditionRepository
import com.google.inject.Inject

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class GameEditionService @Inject()(gameEditionRepository: GameEditionRepository) {
  def handleFindAllGameEditions: Future[GameEditionsResponse] = {
    gameEditionRepository.findAllGameEditions.map(new GameEditionsResponse(_))
  }
}
