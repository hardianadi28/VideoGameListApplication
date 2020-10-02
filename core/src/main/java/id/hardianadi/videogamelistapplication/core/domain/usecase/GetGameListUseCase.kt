package id.hardianadi.videogamelistapplication.core.domain.usecase

import id.hardianadi.videogamelistapplication.core.data.Resource
import id.hardianadi.videogamelistapplication.core.domain.model.Game
import io.reactivex.Flowable

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 25/09/2020
 */
interface GetGameListUseCase {
    fun execute(): Flowable<Resource<List<Game>>>
}