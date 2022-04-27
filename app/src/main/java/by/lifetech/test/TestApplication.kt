package by.lifetech.test

import android.app.Application
import by.lifetech.test.di.testModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Artem Babuk on 27,апрель,2022
 * Skype: archiecrown
 * Telegram: @iBabuk
 */
class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TestApplication)
            modules(testModule)
        }
    }
}