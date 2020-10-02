package id.hardianadi.videogamelistapplication.core.data

import id.hardianadi.videogamelistapplication.core.data.source.local.LocalDataSource
import id.hardianadi.videogamelistapplication.core.data.source.local.entity.GameGenreEntity
import id.hardianadi.videogamelistapplication.core.data.source.local.entity.GamePlatformEntity
import id.hardianadi.videogamelistapplication.core.data.source.remote.RemoteDataSource
import id.hardianadi.videogamelistapplication.core.data.source.remote.network.ApiResponse
import id.hardianadi.videogamelistapplication.core.data.source.remote.response.GameDetailResponse
import id.hardianadi.videogamelistapplication.core.domain.model.Game
import id.hardianadi.videogamelistapplication.core.domain.repository.IGameRepository
import id.hardianadi.videogamelistapplication.core.util.AppExecutors
import id.hardianadi.videogamelistapplication.core.util.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 25/09/2020
 */
class GameRepository(
    private val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource,
    val executor: AppExecutors
) : IGameRepository {
    override fun getGames(): Flowable<Resource<List<Game>>> {
        return object : NetworkBoundResource<List<Game>, List<GameDetailResponse>>() {
            override fun loadFromDB(): Flowable<List<Game>> {
                return localDataSource.getAllGames()
                    .map { DataMapper.mapGameWithPlatformAndGenreListToDomain(it) }
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<GameDetailResponse>>> =
                remoteDataSource.getGames()

            override fun saveCallResult(data: List<GameDetailResponse>) {
                val gameList = data.map { DataMapper.mapGameDetailResponseToEntity(it) }
                localDataSource.insertGame(gameList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()

                for (list in data) {
                    val platformList = DataMapper.mapGameDetailResponseToPlatformEntity(list)
                    localDataSource.insertPlatforms(platformList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()

                    val genreList = DataMapper.mapGameDetailResponseToGenreEntity(list)
                    localDataSource.insertGenre(genreList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()

                    val gamePlatformList = mutableListOf<GamePlatformEntity>()
                    for (platform in platformList) {
                        gamePlatformList.add(GamePlatformEntity(list.id ?: 0, platform.id))
                    }

                    localDataSource.insertGamePlatform(gamePlatformList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()

                    val gameGenreList = mutableListOf<GameGenreEntity>()
                    for (genre in genreList) {
                        gameGenreList.add(GameGenreEntity(list.id ?: 0, genre.id))
                    }

                    localDataSource.insertGameGenre(gameGenreList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
                }


            }
        }.asFlowable()
    }

    override fun getGameDetail(id: Int): Flowable<Resource<Game>> {
        return object : NetworkBoundResource<Game, GameDetailResponse>() {
            override fun loadFromDB(): Flowable<Game> {
                return localDataSource.getGameDetail(id)
                    .map { DataMapper.mapGameWithPlatformAndGenreToDomain(it) }
            }

            override fun shouldFetch(data: Game?): Boolean =
                data == null || data.description.isEmpty()

            override fun createCall(): Flowable<ApiResponse<GameDetailResponse>> =
                remoteDataSource.getGamesDetail(id)

            override fun saveCallResult(data: GameDetailResponse) {
                val game = DataMapper.mapGameDetailResponseToEntity(data)
//                executor.diskIO().execute { localDataSource.updateGameDetail(game)}
                localDataSource.updateGameDetail(game)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()
    }

    override fun setFavorite(game: Game, flag: Boolean) {
        val gameEntity = DataMapper.mapGameDomainToEntity(game)
//        executor.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, flag) }
        localDataSource.setFavoriteGame(gameEntity, flag)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun getAllFavoriteGames(): Flowable<List<Game>> {
        return localDataSource.getAllFavoriteGames()
            .map { DataMapper.mapGameWithPlatformAndGenreListToDomain(it) }
    }

}