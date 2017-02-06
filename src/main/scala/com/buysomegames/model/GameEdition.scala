package com.buysomegames.model

import java.time.LocalDate

import org.bson.types.ObjectId

class GameEdition(
                 val region: String,
                 val name: String,
                 val platform: Platform,
                 var gameId: ObjectId,
                 val releaseDate: LocalDate
                 ) {
}
