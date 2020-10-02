package id.hardianadi.videogamelistapplication.core.util

import id.hardianadi.videogamelistapplication.core.data.source.local.entity.GameEntity
import id.hardianadi.videogamelistapplication.core.data.source.local.entity.GameWithPlatformAndGenre
import id.hardianadi.videogamelistapplication.core.data.source.local.entity.GenreEntity
import id.hardianadi.videogamelistapplication.core.data.source.local.entity.PlatformEntity
import id.hardianadi.videogamelistapplication.core.data.source.remote.response.GameDetailResponse
import id.hardianadi.videogamelistapplication.core.domain.model.Game
import id.hardianadi.videogamelistapplication.core.domain.model.Genre
import id.hardianadi.videogamelistapplication.core.domain.model.Platform

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 25/09/2020
 */
object DataMapper {
    fun mapGameEntityToDomain(gameEntities: List<GameEntity>): List<Game> =
        gameEntities.map { gameEntity ->
            Game(
                gameEntity.id,
                gameEntity.name,
                gameEntity.description,
                gameEntity.backgroundImage,
                gameEntity.backgroundImageAdditional,
                gameEntity.released
            )
        }

    fun mapGameDetailResponseToEntity(response: GameDetailResponse): GameEntity =
        GameEntity(
            response.id ?: 0,
            response.name ?: "",
            response.descriptionRaw ?: "",
            response.backgroundImage ?: "",
            response.backgroundImageAdditional ?: "",
            response.released ?: ""
        )

    fun mapGameDetailResponseToPlatformEntity(response: GameDetailResponse): List<PlatformEntity> {
        val platforms = response.platforms
        val platformEntities = mutableListOf<PlatformEntity>()

        if (platforms != null) {
            for (data in platforms) {
                platformEntities.add(
                    PlatformEntity(
                        data?.platform?.id ?: 0,
                        data?.platform?.name ?: ""
                    )
                )
            }
        }

        return platformEntities
    }

    fun mapGameDetailResponseToGenreEntity(response: GameDetailResponse): List<GenreEntity> {
        val genre = response.genres
        val genreEntities = mutableListOf<GenreEntity>()

        if (genre != null) {
            for (data in genre) {
                genreEntities.add(
                    GenreEntity(
                        data?.id ?: 0,
                        data?.name ?: ""
                    )
                )
            }
        }

        return genreEntities
    }

    fun mapGameWithPlatformAndGenreToDomain(entity: GameWithPlatformAndGenre): Game {
        val game = Game(
            entity.game.id,
            entity.game.name,
            entity.game.description,
            entity.game.backgroundImage,
            entity.game.backgroundImageAdditional,
            entity.game.released
        ).apply { isFavorite = entity.game.isFavorite }

        val platforms = mutableListOf<Platform>()
        for (platform in entity.platforms) {
            val tempPlatform = Platform(platform.id, platform.platformName)
            platforms.add(tempPlatform)
        }

        val genres = mutableListOf<Genre>()
        for (genre in entity.genres) {
            val tempGenre = Genre(genre.id, genre.genreName)
            genres.add(tempGenre)
        }

        game.platform = platforms
        game.genre = genres

        return game
    }

    fun mapGameWithPlatformAndGenreListToDomain(gameEntities: List<GameWithPlatformAndGenre>): List<Game> =
        gameEntities.map { gameEntity ->
            mapGameWithPlatformAndGenreToDomain(gameEntity)
        }

    fun mapGameDomainToEntity(game: Game): GameEntity =
        GameEntity(
            game.gameId,
            game.name,
            game.description,
            game.backgroundImage,
            game.backgroundImageAdditional,
            game.released
        ).apply { isFavorite = game.isFavorite }
}