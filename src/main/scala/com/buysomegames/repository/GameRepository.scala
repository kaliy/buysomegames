package com.buysomegames.repository

import com.buysomegames.model.Game
import com.google.inject.Inject
import org.mongodb.scala.MongoDatabase

import scala.concurrent.Future

class GameRepository @Inject()(
                                db: MongoDatabase
                              ) {
  def findAllGames: Future[Iterable[Game]] = {
    val collection = db.getCollection("games")
    collection.find().map(doc =>
      new Game(
        name = doc.get("name").get.asString().getValue,
        description = doc.get("description").get.asString().getValue
      )
    ).toFuture()
  }
}
