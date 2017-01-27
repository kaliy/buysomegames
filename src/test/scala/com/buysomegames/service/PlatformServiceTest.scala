package com.buysomegames.service

import java.util.concurrent.TimeUnit

import com.buysomegames.controller.response.PlatformsResponse
import com.buysomegames.model.Platform
import com.buysomegames.repository.PlatformRepository
import org.mockito.Mockito.when
import org.scalatest.Matchers._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfterEach, WordSpec}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class PlatformServiceTest extends WordSpec with MockitoSugar with BeforeAndAfterEach {
  var platformService: PlatformService = _
  var mockPlatformRepository: PlatformRepository = _

  override protected def beforeEach(): Unit = {
    mockPlatformRepository = mock[PlatformRepository]
    platformService = new PlatformService(platformRepository = mockPlatformRepository)
  }
  
  "PlatformService.handleFindAllPlatforms" should {
    "create PlatformsResponse from result of PlatformRepository.findAllPlatforms" in {
      val ps3 = new Platform(id = "PS3", name = "Playstation 3")
      when(mockPlatformRepository.findAllPlatforms).thenReturn(Future.successful(Seq(ps3)))
      val responseFuture: Future[PlatformsResponse] = platformService.handleFindAllPlatforms
      val response = Await.result(responseFuture, Duration.create(10, TimeUnit.SECONDS))
      response.platforms should contain only ps3
    }
  }
}
