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
    val NO_RESULTS = "No results found.."

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

    private val _responseData = MutableLiveData<MoonPhaseResponse>().apply {
        value = null
    }
    val responseData: LiveData<MoonPhaseResponse> = _responseData

    private val _error by lazy { MutableLiveData<String>() }
    val error: LiveData<String> by lazy { _error }

    fun getTheMoonPhase() {
        viewModelScope.launch {
            try {
                setLoadingState(true)
                val result = apiContract.moonPhases(System.currentTimeMillis())
                for (i in result.indices) {
                    Timber.d("moonPhase = $result")
                    if (result[i] != null) {
                        result[i].let {
                            _error.postValue("")
                            handleMoonData(it)
                        }
                    } else {
                        _error.postValue("Something went wrong")
                    }
                }
            } catch (e: Exception) {
                Timber.d("moonPhase exception = $e")
                _text.value = NO_RESULTS
                _error.postValue("Something went wrong")
            } finally {
                setLoadingState(false)
            }
        }
    }

    private fun handleMoonData(moonData: MoonPhaseResponse) {
        _responseData.value = moonData // used for unit testing the response
        _distanceText.value = "${moonData.Distance} km"
        _ageText.value = "${(moonData.Age * 100).roundToInt() / 100.0} days"
        _phaseText.value = moonData.Phase
        _setIllumination.postValue(setIllumination(moonData.Illumination))
    }

    fun setLoadingState(state: Boolean) {
        _progressVisibility.postValue(state)
    }

    /**
     * the illumination value is used for determining which image resource to load
     */
    private fun setIllumination(illumination: Double): Int {
        return when {
            illumination < 0.1 -> 0
            illumination in 0.1..0.2 -> {
                1
            }
            illumination in 0.2..0.3 -> {
                2
            }
            illumination in 0.3..0.4 -> {
                3
            }
            illumination in 0.4..0.5 -> {
                4
            }
            illumination in 0.5..0.6 -> {
                5
            }
            illumination in 0.6..0.7 -> {
                6
            }
            illumination in 0.7..0.8 -> {
                7
            }
            illumination in 0.8..0.9 -> {
                8
            }
            illumination in 0.9..1.0 -> {
                9
            }
            else -> {
                10
            }
        }
    }

}