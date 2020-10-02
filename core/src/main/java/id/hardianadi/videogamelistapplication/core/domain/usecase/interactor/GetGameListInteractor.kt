package id.hardianadi.videogamelistapplication.core.domain.usecase.interactor

import id.hardianadi.videogamelistapplication.core.data.Resource
import id.hardianadi.videogamelistapplication.core.domain.model.Game
import id.hardianadi.videogamelistapplication.core.domain.repository.IGameRepository
import id.hardianadi.videogamelistapplication.core.domain.usecase.GetGameListUseCase
import io.reactivex.Flowable

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 25/09/2020
 */
class GetGameListInteractor(private val repository: IGameRepository) :
    GetGameListUseCase {
    override fun execute(): Flowable<Resource<List<Game>>> = repository.getGames()
}