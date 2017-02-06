package com.buysomegames.repository

import java.time.ZoneId

import com.buysomegames.model.{GameEdition, Platform}
import com.google.inject.Inject
import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.bson.collection.Document

import scala.concurrent.Future

class GameEditionRepository @Inject()(
                                       db: MongoDatabase
                                     ) {
  def findAllGameEditions: Future[Iterable[GameEdition]] = {
    val collection = db.getCollection("game_editions")
    collection.find().map(new GameEditionMapper().map(_)).toFuture()
  }

  private class GameEditionMapper {
    def map(doc: Document): GameEdition = {
      val edition = new GameEdition(
        id = doc.getObjectId("_id"),
        name = doc.get("name").get.asString().getValue,
        region = doc.get("region").get.asString.getValue,
        platform = new Platform(id = "PS3", name = "changeit"),
        gameId = doc.getObjectId("game_id"),
        releaseDate = doc.getDate("release_date").toInstant.atZone(ZoneId.of("UTC")).toLocalDate
      )
      edition
    }
  }
}
