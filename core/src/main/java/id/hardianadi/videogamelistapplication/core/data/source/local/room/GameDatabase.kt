package id.hardianadi.videogamelistapplication.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.hardianadi.videogamelistapplication.core.data.source.local.entity.*

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 25/09/2020
 */
@Database(
    entities = [GameEntity::class,
        GenreEntity::class,
        PlatformEntity::class,
        GameGenreEntity::class,
        GamePlatformEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}