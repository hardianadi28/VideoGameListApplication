package id.hardianadi.videogamelistapplication.core.data.source.local.room

import androidx.room.*
import id.hardianadi.videogamelistapplication.core.data.source.local.entity.*
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 25/09/2020
 */
@Dao
interface GameDao {

    @Query("SELECT * FROM games")
    fun getAllGames(): Flowable<List<GameWithPlatformAndGenre>>

    @Transaction
    @Query("SELECT * FROM games WHERE id = :gameId")
    fun getGamesDetail(gameId: Int): Flowable<GameWithPlatformAndGenre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGames(games: List<GameEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGenres(genres: List<GenreEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPlatform(platforms: List<PlatformEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGamePlatform(gamePlatformEntity: List<GamePlatformEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGameGenre(gameGenreEntity: List<GameGenreEntity>): Completable

    @Update
    fun updateGame(game: GameEntity): Completable

    @Query("SELECT * FROM games WHERE is_favorite = 1")
    fun getAllFavoriteGames(): Flowable<List<GameWithPlatformAndGenre>>

}