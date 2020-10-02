package id.hardianadi.videogamelistapplication.core.data.source.remote

import android.annotation.SuppressLint
import id.hardianadi.videogamelistapplication.core.data.source.remote.network.ApiResponse
import id.hardianadi.videogamelistapplication.core.data.source.remote.network.GameServiceApi
import id.hardianadi.videogamelistapplication.core.data.source.remote.response.GameDetailResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 25/09/2020
 */
class RemoteDataSource(private val apiService: GameServiceApi) {

    @SuppressLint("CheckResult")
    fun getGames(): Flowable<ApiResponse<List<GameDetailResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<GameDetailResponse>>>()

        //get data from remote api
        val client = apiService.getGames()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.results
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Timber.e(error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getGamesDetail(gameId: Int): Flowable<ApiResponse<GameDetailResponse>> {
        val resultData = PublishSubject.create<ApiResponse<GameDetailResponse>>()

        //get data from remote api
        val client = apiService.getGameDetail(gameId.toString())

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                resultData.onNext(if (response.id != null) ApiResponse.Success(response) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Timber.e(error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}