package me.dio.copa.catar.domain.model

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
)
