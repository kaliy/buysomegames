package com.buysomegames.kernel

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.{JsonSerializer, SerializerProvider}
import com.twitter.finatra.json.modules.FinatraJacksonModule
import org.bson.types.ObjectId

private class LocalDateSerializer extends JsonSerializer[LocalDate] {
  override def serialize(value: LocalDate, gen: JsonGenerator, serializers: SerializerProvider): Unit = {
    gen.writeString(value.format(DateTimeFormatter.ISO_DATE))
  }
}

private class ObjectIdSerializer extends JsonSerializer[ObjectId] {
  override def serialize(value: ObjectId, gen: JsonGenerator, serializers: SerializerProvider): Unit = {
    gen.writeString(value.toHexString)
  }
}

object BuysomegamesJacksonModule extends FinatraJacksonModule {
  override val additionalJacksonModules = Seq(
    new SimpleModule {
      addSerializer(classOf[LocalDate], new LocalDateSerializer)
    },
    new SimpleModule {
      addSerializer(classOf[ObjectId], new ObjectIdSerializer)
    }
  )
}