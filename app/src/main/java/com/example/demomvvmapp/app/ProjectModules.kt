package com.example.demomvvmapp.app

import com.chuckerteam.chucker.api.Chucker
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.demomvvmapp.BuildConfig
import com.example.demomvvmapp.base.ConnectivityLiveData
import com.example.demomvvmapp.data.api.ApiContract
import com.example.demomvvmapp.data.api.ApiService
import com.example.demomvvmapp.data.api.ProjectAPI
import com.example.demomvvmapp.ui.dashboard.DashboardViewModel
import com.example.demomvvmapp.ui.home.HomeViewModel
import com.example.demomvvmapp.ui.notifications.NotificationsViewModel
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import org.koin.androidx.viewmodel.dsl.viewModel


val allModules
    get() = listOf(
        appModule,
        dataModule,
        homeModule,
        dashboardModule,
        notificationsModule,
    )

val appModule = module {
    single { ConnectivityLiveData(androidContext()) }
}

val dataModule = module {

    single<Converter.Factory> { MoshiConverterFactory.create() }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

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
            .client(get<OkHttpClient>())
            .baseUrl(BuildConfig.API_URL)
            .build()
    }

    single {
        Moshi.Builder().build()
    }

    single<ApiContract> {
        val retrofitApiService = get<Retrofit>().create(ApiService::class.java)
        ProjectAPI(retrofitApiService)
    }
}

val dashboardModule = module {
    viewModel {
        DashboardViewModel()
    }
}

val homeModule = module {
    viewModel {
        HomeViewModel(get())
    }
}

val notificationsModule = module {
    viewModel {
        NotificationsViewModel()
    }
}