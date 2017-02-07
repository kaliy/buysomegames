package com.buysomegames.kernel

import java.time.LocalDate

import com.fasterxml.jackson.databind.ObjectMapper
import com.twitter.inject.app.TestInjector
import org.bson.types.ObjectId
import org.scalatest.{Matchers, WordSpec}

class BuysomegamesJacksonModuleTest extends WordSpec with Matchers {
  "BuysomegamesJacksonModule" when {
    def injector = TestInjector(modules = BuysomegamesJacksonModule)
    val objectMapper: ObjectMapper = injector.instance[ObjectMapper]
    "LocalDate is used" should {
      "serialize it using ISO8601 format" in {
        val date = LocalDate.parse("2007-12-03")
        val result = objectMapper.writeValueAsString(date)
        result shouldBe "\"2007-12-03\""
      }
    }
    "ObjectId is used" should {
      "serialize it as hex string" in {
        val objectId: ObjectId = new ObjectId("5894944f5591d01832058cda")
        val result = objectMapper.writeValueAsString(objectId)
        result shouldBe "\"5894944f5591d01832058cda\""
      }
    }
  }
}
