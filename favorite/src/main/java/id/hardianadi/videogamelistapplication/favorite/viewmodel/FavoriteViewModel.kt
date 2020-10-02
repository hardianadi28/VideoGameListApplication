package id.hardianadi.videogamelistapplication.favorite.viewmodel

import androidx.lifecycle.ViewModel
import id.hardianadi.videogamelistapplication.core.domain.usecase.GetFavoriteGameListUseCase

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 29/09/2020
 */
class FavoriteViewModel(private val useCase: GetFavoriteGameListUseCase) :
    ViewModel() {

    fun getFavoriteGames() = useCase.execute()
}