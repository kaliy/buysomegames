package com.buysomegames.controller

import java.util.concurrent.TimeUnit

import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import org.mongodb.scala._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class GameController @Inject()(
                                db: MongoDatabase
                              ) extends Controller {
  get("/games") { request: Request =>
    val collection: MongoCollection[Document] = db.getCollection("test")
    val l = Await.result(collection.find().toFuture(), Duration(10, TimeUnit.SECONDS))
    "{\"hello\":\"world\"}"
  }
}
