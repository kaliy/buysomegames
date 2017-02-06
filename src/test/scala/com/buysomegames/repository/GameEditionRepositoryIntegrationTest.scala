package com.buysomegames.repository

import java.time.LocalDate
import java.util.concurrent.TimeUnit

import com.buysomegames.kernel.MongoConnectionModule
import com.buysomegames.model.GameEdition
import com.buysomegames.test.FreshDatabase
import com.twitter.inject.app.TestInjector
import com.twitter.inject.{Injector, IntegrationTest}
import org.bson.types.ObjectId

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class GameEditionRepositoryIntegrationTest extends IntegrationTest with FreshDatabase {
  override protected def injector: Injector = TestInjector(modules = Seq(MongoConnectionModule))

  "GameEditionsRepository.findAllGameEditions method" should {
    "return all available game editions" in {
      val gameEditions = getAllGameEditions
      gameEditions should have size 3
    }
    "map GameEdition.name" in {
      val uncharted = getAllGameEditionsAndExtractUncharted
      uncharted.name should be("Uncharted: Drake's Fortune")
    }
    "map GameEdition.gameId" in {
      val uncharted = getAllGameEditionsAndExtractUncharted
      uncharted.gameId should be(new ObjectId("57c28e830868729b8638fe5c"))
    }
    "map GameEdition.name as test entity" in {
      val uncharted = getAllGameEditionsAndExtractUncharted
      uncharted.platform.id should be("PS3")
      uncharted.platform.name should be("changeit")
    }
    "map GameEdition.region from the database" in {
      val uncharted = getAllGameEditionsAndExtractUncharted
      uncharted.region should be("US")
    }
    "map GameEdition.releaseDate from the database" in {
      val uncharted = getAllGameEditionsAndExtractUncharted
      uncharted.releaseDate should be(LocalDate.parse("2007-11-16"))
    }
  }

  private def getAllGameEditionsAndExtractUncharted: GameEdition = {
    val gameEditions: Iterable[GameEdition] = getAllGameEditions
    val uncharted: GameEdition = gameEditions.find(_.id == new ObjectId("5894944f5591d01832058cda")).get
    uncharted
  }

  private def getAllGameEditions: Iterable[GameEdition] = {
    val gameEditionsFuture: Future[Iterable[GameEdition]] = injector.instance[GameEditionRepository].findAllGameEditions
    val gameEditions: Iterable[GameEdition] = Await.result(gameEditionsFuture, Duration(10, TimeUnit.SECONDS))
    gameEditions
  }
}