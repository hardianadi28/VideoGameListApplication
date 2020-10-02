package id.hardianadi.videogamelistapplication.core.domain.usecase.interactor

import id.hardianadi.videogamelistapplication.core.domain.model.Game
import id.hardianadi.videogamelistapplication.core.domain.repository.IGameRepository
import id.hardianadi.videogamelistapplication.core.domain.usecase.GetFavoriteGameListUseCase
import io.reactivex.Flowable

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 29/09/2020
 */
class GetFavoriteGameListInteractor(private val repository: IGameRepository) :
    GetFavoriteGameListUseCase {
    override fun execute(): Flowable<List<Game>> = repository.getAllFavoriteGames()
}