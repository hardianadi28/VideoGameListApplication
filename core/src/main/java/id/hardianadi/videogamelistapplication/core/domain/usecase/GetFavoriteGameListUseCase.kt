package id.hardianadi.videogamelistapplication.core.domain.usecase

import id.hardianadi.videogamelistapplication.core.domain.model.Game
import io.reactivex.Flowable

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 29/09/2020
 */
interface GetFavoriteGameListUseCase {
    fun execute(): Flowable<List<Game>>
}