package com.buysomegames.repository

import com.buysomegames.model.Platform
import com.google.inject.Inject
import org.mongodb.scala.MongoDatabase

import scala.concurrent.Future

class PlatformRepository @Inject()(
                                    db: MongoDatabase
                                  ) {
  def findAllPlatforms: Future[Iterable[Platform]] = {
    val collection = db.getCollection("platforms")
    val documents = collection.find()
    documents.map(doc =>
      new Platform(
        name = doc.get("name").get.asString().getValue,
        id = doc.get("_id").get.asString().getValue
      )
    ).toFuture()
  }
}
