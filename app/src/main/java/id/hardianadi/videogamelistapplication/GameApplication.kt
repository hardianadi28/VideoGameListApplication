package id.hardianadi.videogamelistapplication

import android.app.Application
import id.hardianadi.videogamelistapplication.core.di.databaseModule
import id.hardianadi.videogamelistapplication.core.di.networkModule
import id.hardianadi.videogamelistapplication.core.di.repositoryModule
import id.hardianadi.videogamelistapplication.di.useCaseModule
import id.hardianadi.videogamelistapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 25/09/2020
 */
class GameApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@GameApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}