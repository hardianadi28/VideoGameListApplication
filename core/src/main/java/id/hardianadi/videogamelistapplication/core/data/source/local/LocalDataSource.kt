package id.hardianadi.videogamelistapplication.core.data.source.local

import id.hardianadi.videogamelistapplication.core.data.source.local.entity.*
import id.hardianadi.videogamelistapplication.core.data.source.local.room.GameDao
import io.reactivex.Completable

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 25/09/2020
 */
class LocalDataSource(private val dao: GameDao) {

    fun getAllGames() = dao.getAllGames()

    fun getGameDetail(gameId: Int) = dao.getGamesDetail(gameId)

    fun insertGame(games: List<GameEntity>) = dao.insertGames(games)

    fun insertGenre(genres: List<GenreEntity>) = dao.insertGenres(genres)

    fun insertPlatforms(platforms: List<PlatformEntity>) = dao.insertPlatform(platforms)

    fun insertGameGenre(gameGenre: List<GameGenreEntity>) = dao.insertGameGenre(gameGenre)

    fun insertGamePlatform(gamePlatform: List<GamePlatformEntity>) =
        dao.insertGamePlatform(gamePlatform)

    fun updateGameDetail(game: GameEntity) = dao.updateGame(game)

    fun setFavoriteGame(game: GameEntity, flag: Boolean): Completable {
        game.isFavorite = flag
        return dao.updateGame(game)
    }

    fun getAllFavoriteGames() = dao.getAllFavoriteGames()
}