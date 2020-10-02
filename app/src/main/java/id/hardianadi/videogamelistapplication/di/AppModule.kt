package id.hardianadi.videogamelistapplication.di

import id.hardianadi.videogamelistapplication.core.domain.usecase.GetFavoriteGameListUseCase
import id.hardianadi.videogamelistapplication.core.domain.usecase.GetGameDetailUseCase
import id.hardianadi.videogamelistapplication.core.domain.usecase.GetGameListUseCase
import id.hardianadi.videogamelistapplication.core.domain.usecase.SetFavoriteUseCase
import id.hardianadi.videogamelistapplication.core.domain.usecase.interactor.GetFavoriteGameListInteractor
import id.hardianadi.videogamelistapplication.core.domain.usecase.interactor.GetGameDetailInteractor
import id.hardianadi.videogamelistapplication.core.domain.usecase.interactor.GetGameListInteractor
import id.hardianadi.videogamelistapplication.core.domain.usecase.interactor.SetFavoriteInteractor
import id.hardianadi.videogamelistapplication.viewmodel.DetailViewModel
import id.hardianadi.videogamelistapplication.viewmodel.HomeListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 29/09/2020
 */
val useCaseModule = module {
    factory<GetGameListUseCase> { GetGameListInteractor(get()) }
    factory<GetGameDetailUseCase> { GetGameDetailInteractor(get()) }
    factory<GetFavoriteGameListUseCase> { GetFavoriteGameListInteractor(get()) }
    factory<SetFavoriteUseCase> { SetFavoriteInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeListViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }
}