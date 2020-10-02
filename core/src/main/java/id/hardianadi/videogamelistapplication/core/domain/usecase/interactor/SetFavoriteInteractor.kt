package id.hardianadi.videogamelistapplication.core.domain.usecase.interactor

import id.hardianadi.videogamelistapplication.core.domain.model.Game
import id.hardianadi.videogamelistapplication.core.domain.repository.IGameRepository
import id.hardianadi.videogamelistapplication.core.domain.usecase.SetFavoriteUseCase

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 29/09/2020
 */
class SetFavoriteInteractor(private val repository: IGameRepository) :
    SetFavoriteUseCase {
    override fun execute(game: Game, flag: Boolean) = repository.setFavorite(game, flag)
}