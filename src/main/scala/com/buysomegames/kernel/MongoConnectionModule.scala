package com.buysomegames.kernel

import javax.inject.Singleton

import com.google.inject.Provides
import com.twitter.inject.TwitterModule
import org.mongodb.scala._

object MongoConnectionModule extends TwitterModule {
  @Singleton
  @Provides
  def providesMongoConnection: MongoDatabase = {
    val mongoClient: MongoClient = MongoClient()
    mongoClient.getDatabase("test")
  }
}
