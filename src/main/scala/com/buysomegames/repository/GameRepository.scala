package com.buysomegames.repository

import java.util.concurrent.TimeUnit

import com.buysomegames.model.Game
import com.google.inject.Inject
import org.mongodb.scala.MongoDatabase

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class GameRepository @Inject()(
                                db: MongoDatabase
                              ) {
  def findAllGames: Iterable[Game] = {
    val collection = db.getCollection("games")
    val documents = Await.result(collection.find().toFuture(), Duration(10, TimeUnit.SECONDS))
    documents.map(doc =>
      new Game(
        name = doc.get("name").get.asString().getValue,
        description = doc.get("description").get.asString().getValue
      )
    )
  }
}
