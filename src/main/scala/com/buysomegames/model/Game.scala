package com.buysomegames.model

import com.buysomegames.model.Platform.Platform

class Game(name: String,
           description: String,
           platform: Platform,
           edition: Option[String]
          ) {
}

object Platform extends Enumeration {
  type Platform = Value
  val PS3, PS4, Windows = Value
}

