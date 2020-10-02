package id.hardianadi.videogamelistapplication.favorite.di

import id.hardianadi.videogamelistapplication.favorite.viewmodel.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 29/09/2020
 */
val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}