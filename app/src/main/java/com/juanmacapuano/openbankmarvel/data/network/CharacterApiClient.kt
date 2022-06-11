package com.juanmacapuano.openbankmarvel.data.network

import com.juanmacapuano.openbankmarvel.data.model.CharacterDTO
import com.juanmacapuano.openbankmarvel.data.model.CharactersDTO
import com.juanmacapuano.openbankmarvel.utils.Utils
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiClient {

    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("apikey")apikey:String=Utils.API_KEY,
        @Query("ts")ts:String=Utils.ts,
        @Query("hash")hash:String=Utils.hash(),
        @Query("offset")offset:String="20"
    ): CharactersDTO

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacter(
        @Path("characterId")characterId:Int,
        @Query("apikey")apikey:String=Utils.API_KEY,
        @Query("ts")ts:String=Utils.ts,
        @Query("hash")hash:String=Utils.hash(),
        @Query("offset")offset:String="20"
    ): CharacterDTO
}