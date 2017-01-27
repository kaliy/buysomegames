package com.buysomegames.service

import com.buysomegames.controller.response.PlatformsResponse
import com.buysomegames.repository.PlatformRepository
import com.google.inject.Inject

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class PlatformService @Inject()(platformRepository: PlatformRepository) {
  def handleFindAllPlatforms: Future[PlatformsResponse] = {
    platformRepository.findAllPlatforms.map(new PlatformsResponse(_))
  }
}
