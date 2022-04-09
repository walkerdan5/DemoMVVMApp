package com.example.demomvvmapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.demomvvmapp.data.api.ApiContract
import com.example.demomvvmapp.data.models.MoonPhaseResponse
import com.example.demomvvmapp.ui.home.HomeViewModel
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.component.KoinApiExtension
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@OptIn(KoinApiExtension::class)
@RunWith(JUnit4::class)
class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var apiContract: ApiContract

    @Rule @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initSetUp(){
        apiContract = Mockito.mock(ApiContract::class.java)
        homeViewModel = HomeViewModel(apiContract)
    }

    @Test
    fun checkLoadingState_OnRequestInit_isTrue(){
        homeViewModel.setLoadingState(true)
        Truth.assertThat(homeViewModel.progressVisibility.value).isEqualTo(true)
    }

    @Test
    fun checkLoadingState_OnRequestComplete_isFalse(){
        homeViewModel.setLoadingState(false)
        Truth.assertThat(homeViewModel.progressVisibility.value).isFalse()
    }

    @Test
    fun onResponseReceived_checkFailedState_isError(){
        Mockito.`when`(homeViewModel.getTheMoonPhase()).thenReturn(null)
        Truth.assertThat(homeViewModel.text.value).isEqualTo(homeViewModel.NO_RESULTS)
        Truth.assertThat(homeViewModel.progressVisibility.value).isFalse()
    }

    @Test
    fun onResponseReceived_checkSuccessState_isSuccess(){
        Mockito.`when`(homeViewModel.getTheMoonPhase())
        Truth.assertThat(homeViewModel.responseData.value != null)
        Truth.assertThat(homeViewModel.error.value == "")
        Truth.assertThat(homeViewModel.progressVisibility.value).isEqualTo(false)
    }

//    @Test
//    fun onResponseReceived_checkCorrectMoonDrawableIsSet() {
//        Mockito.`when`(homeViewModel.setIllumination(0.1346))
//        Truth.assertThat(homeViewModel.setIllumination.value).isEqualTo(1)
//    }
}