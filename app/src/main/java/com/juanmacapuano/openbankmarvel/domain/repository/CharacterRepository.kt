package com.juanmacapuano.openbankmarvel.domain.repository

import com.juanmacapuano.openbankmarvel.domain.model.CharacterModel

interface CharacterRepository {

    suspend fun getAllCharacters(): List<CharacterModel>?

    suspend fun getCharacterFromApi(id: Int): List<CharacterModel>
}