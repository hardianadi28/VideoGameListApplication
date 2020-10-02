package id.hardianadi.videogamelistapplication.viewmodel

import androidx.lifecycle.ViewModel
import id.hardianadi.videogamelistapplication.core.domain.model.Game
import id.hardianadi.videogamelistapplication.core.domain.usecase.GetGameDetailUseCase
import id.hardianadi.videogamelistapplication.core.domain.usecase.SetFavoriteUseCase

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 29/09/2020
 */
class DetailViewModel(
    private val useCase: GetGameDetailUseCase,
    private val useCaseFavorite: SetFavoriteUseCase
) :
    ViewModel() {

    fun getGameDetail(id: Int) = useCase.execute(id)

    fun setFavoriteGame(game: Game, flag: Boolean) =
        useCaseFavorite.execute(game, flag)
}