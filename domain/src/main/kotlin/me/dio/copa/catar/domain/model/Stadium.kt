package me.dio.copa.catar.domain.model

typealias StadiumDomain = Stadium

data class Stadium(
    val name: String,
    val image: String
) {
    companion object {
        val DUMB_STADIUM = Stadium("BRASIL" , "../docs/statics/974-stadium.png")
    }
}
