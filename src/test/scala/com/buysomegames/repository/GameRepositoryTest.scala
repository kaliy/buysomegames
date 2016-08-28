package com.buysomegames.repository

import com.buysomegames.kernel.MongoConnectionModule
import com.buysomegames.model.Game
import com.buysomegames.test.FreshDatabase
import com.twitter.inject.app.TestInjector
import com.twitter.inject.{Injector, IntegrationTest}

class GameRepositoryTest extends IntegrationTest with FreshDatabase {
  override protected def injector: Injector = TestInjector(modules = Seq(MongoConnectionModule))

  "GameRepository.findAllGames" should {
    "return all available games" in {
      val games: Iterable[Game] = injector.instance[GameRepository].findAllGames
      games should have size 2
      games.map(_.name) should contain only ("Uncharted: Drake’s Fortune", "Gravity Rush")
    }
  }
}
