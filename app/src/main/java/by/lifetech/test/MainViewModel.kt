package by.lifetech.test

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.lifetech.test.entity.SimpleEntity
import by.lifetech.test.interactor.GetTestInteractor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by Artem Babuk on 27,апрель,2022
 * Skype: archiecrown
 * Telegram: @iBabuk
 */
class MainViewModel(private val interactor: GetTestInteractor) : ViewModel() {

    private val data: Channel<List<SimpleEntity>> = Channel()

    fun getDataChannel() = data.receiveAsFlow()

    fun getData() {
        viewModelScope.launch {
            interactor
                .getModels()
                .onEach {
                    data.send(it)
                }
                .catch {
                    Timber.e(it)
                }
                .launchIn(viewModelScope)
        }
    }
}