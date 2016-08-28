package com.buysomegames.repository

import java.util.concurrent.TimeUnit
import javax.inject.Inject

import com.buysomegames.kernel.MongoConnectionModule
import com.twitter.inject.app.TestInjector
import com.twitter.inject.{Injector, IntegrationTest}
import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.bson.collection.immutable.Document

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.Source

class GameRepositoryTest extends IntegrationTest {
  override protected def injector: Injector = TestInjector(modules = Seq(MongoConnectionModule))

  "GameRepository.findAllGames" should {
    "return all available games" in {
      assert(injector.instance[GameRepository].findAllGames.size == 2)
    }
  }

  override protected def beforeEach(): Unit = {
    val collection = injector.instance[MongoDatabase].getCollection("games")
    val lines = Source.fromInputStream(getClass.getResourceAsStream("/data/games.json")).getLines
    Await.result(collection.drop().head(), Duration(10, TimeUnit.SECONDS))
    Await.result(collection.insertMany(lines.map(json => Document(json)).toSeq).toFuture(), Duration(10, TimeUnit.SECONDS))
  }
}
