package com.juanmacapuano.openbankmarvel.domain.repository

import com.juanmacapuano.openbankmarvel.data.model.CharacterDTO
import com.juanmacapuano.openbankmarvel.data.model.CharactersDTO

interface CharacterRepository {

    suspend fun getAllCharacters(): CharactersDTO

    suspend fun getCharacterFromApi(id: Int): CharacterDTO
}