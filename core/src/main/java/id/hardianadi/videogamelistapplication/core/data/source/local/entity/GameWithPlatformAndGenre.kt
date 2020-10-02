package id.hardianadi.videogamelistapplication.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 25/09/2020
 */
data class GameWithPlatformAndGenre(
    @Embedded
    val game: GameEntity,

    @Relation(
        parentColumn = "id",
        entity = PlatformEntity::class,
        entityColumn = "id",
        associateBy = Junction(
            value = GamePlatformEntity::class,
            parentColumn = "game_id",
            entityColumn = "platform_id"
        )
    )
    val platforms: List<PlatformEntity>,

    @Relation(
        parentColumn = "id",
        entity = GenreEntity::class,
        entityColumn = "id",
        associateBy = Junction(
            value = GameGenreEntity::class,
            parentColumn = "game_id",
            entityColumn = "genre_id"
        )
    )
    val genres: List<GenreEntity>
)