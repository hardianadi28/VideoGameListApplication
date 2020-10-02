package id.hardianadi.videogamelistapplication.core.domain.usecase

import id.hardianadi.videogamelistapplication.core.domain.model.Game

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 29/09/2020
 */
interface SetFavoriteUseCase {
    fun execute(game: Game, flag: Boolean)
}