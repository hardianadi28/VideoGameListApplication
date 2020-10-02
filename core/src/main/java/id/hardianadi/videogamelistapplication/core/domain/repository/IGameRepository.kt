package id.hardianadi.videogamelistapplication.core.domain.repository

import id.hardianadi.videogamelistapplication.core.data.Resource
import id.hardianadi.videogamelistapplication.core.domain.model.Game
import io.reactivex.Flowable

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 25/09/2020
 */
interface IGameRepository {

    fun getGames(): Flowable<Resource<List<Game>>>

    fun getGameDetail(id: Int): Flowable<Resource<Game>>

    fun setFavorite(game: Game, flag: Boolean)

    fun getAllFavoriteGames(): Flowable<List<Game>>
}