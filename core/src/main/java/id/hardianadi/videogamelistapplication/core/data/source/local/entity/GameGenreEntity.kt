package id.hardianadi.videogamelistapplication.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 25/09/2020
 */
@Entity(tableName = "game_genre", primaryKeys = ["game_id", "genre_id"])
data class GameGenreEntity(
    @ColumnInfo(name = "game_id")
    val gameId: Int,

    @ColumnInfo(name = "genre_id")
    val genreId: Int
)