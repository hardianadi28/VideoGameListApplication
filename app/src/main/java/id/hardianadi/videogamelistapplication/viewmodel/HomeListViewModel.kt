package id.hardianadi.videogamelistapplication.viewmodel

import androidx.lifecycle.ViewModel
import id.hardianadi.videogamelistapplication.core.domain.usecase.GetGameListUseCase

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 28/09/2020
 */
class HomeListViewModel(private val useCase: GetGameListUseCase) :
    ViewModel() {

    fun getGameList() = useCase.execute()
}