package com.pokedex.inject

import android.app.Application
import com.pokedex.PokedexApplication
import com.pokedex.feature.PokedexActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CommonsModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
    ],
)
interface PokedexComponent {
    fun inject(activity: PokedexActivity)

    fun inject(application: PokedexApplication)
}