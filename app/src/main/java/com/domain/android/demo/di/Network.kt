package com.domain.android.demo.di

import com.domain.android.demo.BuildConfig
import com.domain.android.demo.data.remote.SearchPhotosService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
val networkModule = module {
    factory { provideInterceptors() }
    factory { provideOkHttpClient(get()) }
    factory { provideSearchApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideInterceptors():List<Interceptor> {
    return buildList {
        add(urlInterceptor())
        if(BuildConfig.DEBUG){
            add(loggingInterceptor())
        }
    }
}

private fun urlInterceptor() = Interceptor {
    val newUrl = it.request().url
        .newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY)
        .addQueryParameter("format", "json")
        .addQueryParameter("nojsoncallback", "1")
        .build()
    val newRequest = it.request()
        .newBuilder()
        .url(newUrl)
        .build()
    it.proceed(newRequest)
}
private fun loggingInterceptor():Interceptor{
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return interceptor
}
private fun provideOkHttpClient(interceptor: List<Interceptor>):OkHttpClient {
    val newBuilder = OkHttpClient().newBuilder()
    interceptor.forEach {
        newBuilder.addInterceptor(it)
    }
    return newBuilder.build()
}


private fun provideRetrofit(client: OkHttpClient) =
    Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.FLICKR_BASE_URL).client(client).build()

fun provideSearchApi(retrofit: Retrofit): SearchPhotosService =
    retrofit.create(SearchPhotosService::class.java)
