package com.example.demomvvmapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.demomvvmapp.base.BaseViewModel
import com.example.demomvvmapp.data.api.ApiContract
import com.example.demomvvmapp.data.models.MoonPhaseResponse
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import timber.log.Timber
import kotlin.math.roundToInt

@KoinApiExtension
class HomeViewModel(private val apiContract: ApiContract) : BaseViewModel<HomeEvent>() {

    private val INITIAL_MESSAGE = "Get current moon phase"
    private val NO_RESULTS = "No results found.."
    private val SEARCHING = "Getting lit moon data..."

    private val _text = MutableLiveData<String>().apply {
        value = INITIAL_MESSAGE
    }
    val text: LiveData<String> = _text

    private val _distanceText = MutableLiveData<String>().apply {
        value = ""
    }
    val distanceText: LiveData<String> = _distanceText

    private val _ageText = MutableLiveData<String>().apply {
        value = ""
    }
    val ageText: LiveData<String> = _ageText

    private val _phaseText = MutableLiveData<String>().apply {
        value = ""
    }
    val phaseText: LiveData<String> = _phaseText


    private val _progressVisibility = MutableLiveData<Boolean>().apply { false }
    val progressVisibility: LiveData<Boolean> = _progressVisibility

    private val _setIllumination = MutableLiveData<Int>()
    val setIllumination: LiveData<Int> = _setIllumination


    fun getTheMoonPhase() {
        viewModelScope.launch {
            try {
                _progressVisibility.value = true
                val result = apiContract.moonPhases(System.currentTimeMillis())
                for (i in result.indices) {
                    Timber.d("moonPhase = $result")
                    result[i].let {
                        _distanceText.value = "${it.Distance} km"
                        _ageText.value = "${(it.Age * 100).roundToInt()/100.0} days"
                        _phaseText.value = it.Phase
                        setIllumination(it.Illumination)
                    }
                }
            } catch (e: Exception) {
                Timber.d("moonPhase exception = $e")
                _text.value = NO_RESULTS
            } finally {
                _progressVisibility.value = false
            }
        }
    }

    private fun setIllumination(illumination: Double) {
        when {
            illumination < 0.1 -> _setIllumination.postValue(0)
            illumination in 0.1..0.2 -> {
                _setIllumination.postValue(1)
            }
            illumination in 0.2..0.3 -> {
                _setIllumination.postValue(2)
            }
            illumination in 0.3..0.4 -> {
                _setIllumination.postValue(3)
            }
            illumination in 0.4..0.5 -> {
                _setIllumination.postValue(4)
            }
            illumination in 0.5..0.6 -> {
                _setIllumination.postValue(5)
            }
            illumination in 0.6..0.7 -> {
                _setIllumination.postValue(6)
            }
            illumination in 0.7..0.8 -> {
                _setIllumination.postValue(7)
            }
            illumination in 0.8..0.9 -> {
                _setIllumination.postValue(8)
            }
            illumination in 0.9..1.0 -> {
                _setIllumination.postValue(9)
            }
            else -> {
                _setIllumination.postValue(10)
            }
        }


    }

    fun findClicked() {
        //postEvent(HomeEvent.FindClicked)
    }
}