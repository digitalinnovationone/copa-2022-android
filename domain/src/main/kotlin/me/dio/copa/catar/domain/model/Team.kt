package me.dio.copa.catar.domain.model

typealias TeamDomain = Team

data class Team(
    val flag: String,
    val displayName: String
) {
    companion object {
        val DUMB_TEAM = Team("CD", "AB")
    }
}
