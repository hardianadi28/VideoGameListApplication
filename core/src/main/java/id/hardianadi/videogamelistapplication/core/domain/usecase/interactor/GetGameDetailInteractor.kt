package id.hardianadi.videogamelistapplication.core.domain.usecase.interactor

import id.hardianadi.videogamelistapplication.core.data.Resource
import id.hardianadi.videogamelistapplication.core.domain.model.Game
import id.hardianadi.videogamelistapplication.core.domain.repository.IGameRepository
import id.hardianadi.videogamelistapplication.core.domain.usecase.GetGameDetailUseCase
import io.reactivex.Flowable

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 25/09/2020
 */
class GetGameDetailInteractor(private val repository: IGameRepository) :
    GetGameDetailUseCase {
    override fun execute(id: Int): Flowable<Resource<Game>> = repository.getGameDetail(id)
}