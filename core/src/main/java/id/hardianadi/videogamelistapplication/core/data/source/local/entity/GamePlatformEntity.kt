package id.hardianadi.videogamelistapplication.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 25/09/2020
 */
@Entity(tableName = "game_platform", primaryKeys = ["game_id", "platform_id"])
data class GamePlatformEntity(
    @ColumnInfo(name = "game_id")
    val gameId: Int,

    @ColumnInfo(name = "platform_id")
    val platformId: Int
)