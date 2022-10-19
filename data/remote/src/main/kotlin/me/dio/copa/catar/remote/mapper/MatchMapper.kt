package me.dio.copa.catar.remote.mapper

import me.dio.copa.catar.domain.model.MatchDomain
import me.dio.copa.catar.domain.model.StadiumDomain
import me.dio.copa.catar.domain.model.Team
import me.dio.copa.catar.remote.model.MatchRemote
import me.dio.copa.catar.remote.model.StadiumRemote
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import java.util.Locale

internal fun List<MatchRemote>.toDomain() = map { it.toDomain() }

fun MatchRemote.toDomain(): MatchDomain {
    return MatchDomain(
        id = "$team1-$team2",
        name = name,
        team1 = team1.toTeam(),
        team2 = team2.toTeam(),
        stadium = stadium.toDomain(),
        date = date.toLocalDateTime(),
    )
}

private fun Date.toLocalDateTime(): LocalDateTime {
    return toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
}

private fun String.toTeam(): Team {
    return Team(
        flag = getTeamFlag(this),
        displayName = Locale("", this).isO3Country
    )
}

private fun getTeamFlag(team: String): String {
    return team.map {
        String(Character.toChars(it.code + 127397))
    }.joinToString("")
}

fun StadiumRemote.toDomain(): StadiumDomain {
    return StadiumDomain(
        name = name,
        image = image
    )
}
