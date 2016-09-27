package com.buysomegames.repository

import java.util.concurrent.TimeUnit

import com.buysomegames.kernel.MongoConnectionModule
import com.buysomegames.model.Game
import com.buysomegames.test.FreshDatabase
import com.twitter.inject.app.TestInjector
import com.twitter.inject.{Injector, IntegrationTest}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class GameRepositoryTest extends IntegrationTest with FreshDatabase {
  override protected def injector: Injector = TestInjector(modules = Seq(MongoConnectionModule))

  "GameRepository.findAllGames" should {
    "return all available games" in {
      val gamesFuture: Future[Iterable[Game]] = injector.instance[GameRepository].findAllGames
      val games: Iterable[Game] = Await.result(gamesFuture, Duration(10, TimeUnit.SECONDS))
      games should have size 2
      games.map(_.name) should contain only ("Uncharted: Drake’s Fortune", "Gravity Rush")
    }
  }
}
