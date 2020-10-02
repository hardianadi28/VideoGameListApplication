package id.hardianadi.videogamelistapplication.core.data.source.remote.network

import id.hardianadi.videogamelistapplication.core.data.source.remote.response.GameDetailResponse
import id.hardianadi.videogamelistapplication.core.data.source.remote.response.GamesResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 25/09/2020
 */
interface GameServiceApi {

    @GET("games")
    fun getGames(): Flowable<GamesResponse>

    @GET("games/{gameId}")
    fun getGameDetail(@Path("gameId") gameId: String): Flowable<GameDetailResponse>
}