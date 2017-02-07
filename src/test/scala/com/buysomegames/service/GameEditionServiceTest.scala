package com.buysomegames.service

import java.time.LocalDate
import java.util.concurrent.TimeUnit

import com.buysomegames.controller.response.GameEditionsResponse
import com.buysomegames.model.{GameEdition, GameEditionGame, Platform}
import com.buysomegames.repository.GameEditionRepository
import org.bson.types.ObjectId
import org.mockito.Mockito.when
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfterEach, Matchers, WordSpec}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class GameEditionServiceTest extends WordSpec with MockitoSugar with Matchers with BeforeAndAfterEach {
  var gameEditionService: GameEditionService = _
  var mockGameEditionRepository: GameEditionRepository = _

  override protected def beforeEach(): Unit = {
    mockGameEditionRepository = mock[GameEditionRepository]
    gameEditionService = new GameEditionService(gameEditionRepository = mockGameEditionRepository)
  }

  "GameService.handleFindAllGames" should {
    "create GamesResponse from result of GameRepository.findAllGames" in {
      val gameEdition = new GameEdition(
        id = new ObjectId(),
        name = "Uncharted",
        region = "US",
        platform = new Platform(id = "PS3", name = "Playstation 3"),
        game = new GameEditionGame(id = new ObjectId(), name = "Uncharted"),
        releaseDate = LocalDate.parse("2007-11-16")
      )
      when(mockGameEditionRepository.findAllGameEditions).thenReturn(Future.successful(Seq(gameEdition)))
      val responseFuture: Future[GameEditionsResponse] = gameEditionService.handleFindAllGameEditions
      val response = Await.result(responseFuture, Duration.create(10, TimeUnit.SECONDS))
      response.gameEditions should contain only gameEdition
    }
  }
}