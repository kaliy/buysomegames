package com.buysomegames.test

import java.util.concurrent.TimeUnit

import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.bson.collection.immutable.Document

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.Source

trait FreshDatabase extends com.twitter.inject.IntegrationTest {
  override protected def beforeEach(): Unit = {
    val collection = injector.instance[MongoDatabase].getCollection("games")
    val lines = Source.fromInputStream(getClass.getResourceAsStream("/data/games.json")).getLines
    Await.result(collection.drop().head(), Duration(10, TimeUnit.SECONDS))
    Await.result(collection.insertMany(lines.map(json => Document(json)).toSeq).toFuture(), Duration(10, TimeUnit.SECONDS))
  }
}