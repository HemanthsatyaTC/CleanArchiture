package com.hemanth.cleanarchiture.di

import com.google.gson.Gson
import com.hemanth.data.remote.SuperHeroApiDetails.BASE_URL
import com.hemanth.data.remote.SuperHeroInterface
import com.hemanth.data.repository.Repository
import com.hemanth.data.repository.RepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun getGson(): Gson { //return type is how the hilt will know what to return
        //Function Name itself serves No purpose
        return Gson()
    }

    @Provides
    fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }


    @Provides
    fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun SuperHeroInterface(
        retrofit: Retrofit
    ): SuperHeroInterface {
        return retrofit.create(SuperHeroInterface::class.java)

    }

    @Provides
    fun getRepository(
        apiHero: SuperHeroInterface
        ): Repository {
        return RepositoryImplementation(apiHero)

    }
}