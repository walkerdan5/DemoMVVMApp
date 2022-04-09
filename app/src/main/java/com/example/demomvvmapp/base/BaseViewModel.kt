package com.example.demomvvmapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.demomvvmapp.common.SingleLiveEvent
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent


@KoinApiExtension
abstract class BaseViewModel<T: BaseEvent>: ViewModel(), KoinComponent {

    private val _events = SingleLiveEvent<T>()
    val events: LiveData<T>
        get() = _events

    protected fun postEvent(event: T?, sync: Boolean = false) {
        if (sync) {
            _events.value = event!!
        } else {
            _events.postValue(event!!)
        }
    }
}