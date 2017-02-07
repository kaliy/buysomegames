package com.buysomegames.model

import java.time.LocalDate

import org.bson.types.ObjectId

class GameEdition(
                   val id: ObjectId,
                   val region: String,
                   val name: String,
                   val platform: Platform,
                   val game: GameEditionGame,
                   val releaseDate: LocalDate
                 ) {
}

class GameEditionGame(val id: ObjectId,
                      val name: String) {
}

