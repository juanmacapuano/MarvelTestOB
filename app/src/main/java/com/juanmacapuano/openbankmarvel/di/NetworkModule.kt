package com.juanmacapuano.openbankmarvel.di

import com.juanmacapuano.openbankmarvel.data.network.CharacterApiClient
import com.juanmacapuano.openbankmarvel.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Utils.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterApiClient(retrofit: Retrofit): CharacterApiClient {
        return retrofit.create(CharacterApiClient::class.java)
    }

    /*@Provides
    @Singleton
    fun provideCharacterRepository(apiClient: CharacterService): CharacterRepository {
        return CharactersRepositoryImpl(apiClient)
    }*/
}