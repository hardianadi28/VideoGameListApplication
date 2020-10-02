package id.hardianadi.videogamelistapplication.core.domain.model

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 25/09/2020
 */
data class Game(
    val gameId: Int,
    val name: String,
    val description: String,
    val backgroundImage: String,
    val backgroundImageAdditional: String,
    val released: String
) {
    var platform: List<Platform> = listOf()
    var genre: List<Genre> = listOf()
    var isFavorite: Boolean = false
}