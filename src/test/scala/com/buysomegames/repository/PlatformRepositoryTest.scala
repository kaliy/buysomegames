package com.buysomegames.repository

import java.util.concurrent.TimeUnit

import com.buysomegames.kernel.MongoConnectionModule
import com.buysomegames.model.Platform
import com.buysomegames.test.FreshDatabase
import com.twitter.inject.app.TestInjector
import com.twitter.inject.{Injector, IntegrationTest}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class PlatformRepositoryTest extends IntegrationTest with FreshDatabase {
  override protected def injector: Injector = TestInjector(modules = Seq(MongoConnectionModule))

  "PlatformRepository.findAllPlatforms" should {
    "return all available games" in {
      val platformsFuture: Future[Iterable[Platform]] = injector.instance[PlatformRepository].findAllPlatforms
      val platforms: Iterable[Platform] = Await.result(platformsFuture, Duration(10, TimeUnit.SECONDS))
      platforms should have size 4
      platforms.map(_.name) should contain only("Playstation 1", "Playstation 2", "Playstation 3", "Playstation 4")
      platforms.map(_.id) should contain only("PS1", "PS2", "PS3", "PS4")
    }
  }
}
