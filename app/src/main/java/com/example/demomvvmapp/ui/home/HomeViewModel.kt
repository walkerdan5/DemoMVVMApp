package com.example.demomvvmapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.demomvvmapp.base.BaseViewModel
import com.example.demomvvmapp.data.api.ApiContract
import com.example.demomvvmapp.data.models.MoonPhaseResponse
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import timber.log.Timber

@KoinApiExtension
class HomeViewModel(private val apiContract: ApiContract) : BaseViewModel<HomeEvent>(){

    private val INITIAL_MESSAGE = "Get current moon phase"
    private val NO_RESULTS = "No results found.."
    private val SEARCHING = "Getting lit moon data..."

    private val _text = MutableLiveData<String>().apply {
        value = INITIAL_MESSAGE
    }
    val text: LiveData<String> = _text

    private val _moonPhase = MutableLiveData<MoonPhaseResponse>()
    val moonPhase: LiveData<MoonPhaseResponse> = _moonPhase

    private val _progressVisibility = MutableLiveData<Boolean>().apply { false }
    val progressVisibility: LiveData<Boolean> = _progressVisibility

    fun getTheMoonPhase() {
        viewModelScope.launch {
            try {
                _progressVisibility.value = true
                    val result = apiContract.moonPhases(System.currentTimeMillis())
                     _text.value = result.toString()
                Timber.d("moonPhase = $result")
                    //if(result.isEmpty()) _text.value = NO_RESULTS

            } catch (e: Exception) {
                Timber.d("moonPhase exception = $e")
                _text.value = NO_RESULTS
            } finally {
                _progressVisibility.value = false
            }
        }
    }

    private fun clearUniListForSearch() {
        //_uniList.value = emptyList()
        _text.value = SEARCHING
    }

    fun findClicked() {
        //postEvent(HomeEvent.FindClicked)
    }
}