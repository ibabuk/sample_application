package by.lifetech.test.di

import by.lifetech.test.MainViewModel
import by.lifetech.test.interactor.GetTestInteractor
import by.lifetech.test.interactor.GetTestMapper
import by.lifetech.test.repository.AssetsRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Artem Babuk on 27,апрель,2022
 * Skype: archiecrown
 * Telegram: @iBabuk
 */

val testModule = module {
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single { AssetsRepository(context = get(), moshi = get()) }
    single { GetTestMapper() }
    single { GetTestInteractor(repository = get(), mapper = get()) }

    viewModel { MainViewModel(interactor = get()) }
}