package com.buysomegames.repository

import java.time.ZoneId

import com.buysomegames.model.{GameEdition, GameEditionGame, Platform}
import com.google.inject.Inject
import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.bson.collection.Document

import scala.concurrent.Future

class GameEditionRepository @Inject()(
                                       db: MongoDatabase
                                     ) {
  def findAllGameEditions: Future[Iterable[GameEdition]] = {
    val collection = db.getCollection("game_editions")
    collection.find().map(mapGameEdition).toFuture()
  }

  private def mapGameEdition(doc: Document): GameEdition = {
    val gameDocument = doc.get[BsonDocument]("game").get
    val game = new GameEditionGame(
      id = gameDocument.getObjectId("_id").getValue,
      name = gameDocument.getString("name").getValue
    )
    new GameEdition(
      id = doc.getObjectId("_id"),
      name = doc.get("name").get.asString().getValue,
      region = doc.get("region").get.asString.getValue,
      platform = new Platform(id = "PS3", name = "changeit"),
      game = game,
      releaseDate = doc.getDate("release_date").toInstant.atZone(ZoneId.of("UTC")).toLocalDate
    )
  }
}
