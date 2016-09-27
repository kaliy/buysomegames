package com.buysomegames.service

import java.util.concurrent.TimeUnit

import com.buysomegames.controller.response.GamesResponse
import com.buysomegames.model.Game
import com.buysomegames.repository.GameRepository
import org.mockito.Mockito
import org.scalatest.Matchers._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfterEach, WordSpec}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class GameServiceTest extends WordSpec with MockitoSugar with BeforeAndAfterEach {
  var gameService: GameService = _
  var mockGameRepository: GameRepository = _

  override protected def beforeEach(): Unit = {
    mockGameRepository = mock[GameRepository]
    gameService = new GameService(gameRepository = mockGameRepository)
  }

  "GameService.handleFindAllGames" should {
    "create GamesResponse from result of GameRepository.findAllGames" in {
      val pokemonRed = new Game(name = "Pokemon Red", description = "Boring :(")
      Mockito.when(mockGameRepository.findAllGames).thenReturn(
        Future.successful(Seq(pokemonRed)))
      val responseFuture: Future[GamesResponse] = gameService.handleFindAllGames
      val response = Await.result(responseFuture, Duration.create(10, TimeUnit.SECONDS))
      response.games should contain only pokemonRed
    }
  }
}
