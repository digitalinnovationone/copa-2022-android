package me.dio.copa.catar.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

typealias MatchDomain = Match

data class Match(
    val id: String,
    val name: String,
    val stadium: Stadium,
    val team1: Team,
    val team2: Team,
    val date: LocalDateTime,
    val notificationEnabled: Boolean = false,
) {
    companion object {

        val DUMB_MATCH = Match(
            id ="1",
            name = "Mexiricao",
           stadium = Stadium.DUMB_STADIUM,
            team1 = Team.DUMB_TEAM,
            team2 = Team.DUMB_TEAM,
            date = LocalDateTime.now(),
            notificationEnabled = false
        )
        val DUMB_LIST = listOf(
            DUMB_MATCH, DUMB_MATCH, DUMB_MATCH
        )
    }
}
