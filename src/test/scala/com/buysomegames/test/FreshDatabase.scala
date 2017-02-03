package com.buysomegames.test

import java.util.concurrent.TimeUnit

import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.bson.collection.immutable.Document

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.Source

trait FreshDatabase extends com.twitter.inject.IntegrationTest {
  override protected def beforeEach(): Unit = {
    insertDataFromResourceIntoCollection("/data/games.json", "games")
    insertDataFromResourceIntoCollection("/data/platforms.json", "platforms")
    insertDataFromResourceIntoCollection("/data/game_editions.json", "game_editions")
  }

  private def insertDataFromResourceIntoCollection(json: String, collection: String): Unit = {
    val mongoCollection = injector.instance[MongoDatabase].getCollection(collection)
    val lines = Source.fromInputStream(getClass.getResourceAsStream(json)).getLines
    Await.result(mongoCollection.drop().head(), Duration(10, TimeUnit.SECONDS))
    Await.result(mongoCollection.insertMany(lines.map(json => Document(json)).toSeq).toFuture(), Duration(10, TimeUnit.SECONDS))
  }
}