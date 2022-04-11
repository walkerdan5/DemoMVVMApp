package com.example.demomvvmapp.koin

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.demomvvmapp.BuildConfig
import com.example.demomvvmapp.data.api.ApiContract
import com.example.demomvvmapp.data.api.ApiService
import com.example.demomvvmapp.data.api.ProjectAPI
import com.example.demomvvmapp.ui.home.HomeViewModel
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val allModules
    get() = listOf(
        dataModule,
        homeModule
    )

val dataModule = module {
    // Network dependencies
    single<Converter.Factory> { MoshiConverterFactory.create() }

    single {
        OkHttpClient.Builder()
            .addInterceptor(
                ChuckerInterceptor.Builder(androidContext())
                    .collector(ChuckerCollector(androidContext()))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(get())
            .client(get())
            .baseUrl("${BuildConfig.API_URL}/")
            .build()
    }

    single {
        Moshi.Builder().build()
    }

    single<ApiContract> {
        val retrofitApiService = get<Retrofit>().create(ApiService::class.java)
        ProjectAPI(retrofitApiService)
    }
    // Network dependencies END


}

@OptIn(KoinApiExtension::class)
val homeModule = module {
    viewModel {
        HomeViewModel(get())
    }
}