package com.juanmacapuano.openbankmarvel.data.network

import com.juanmacapuano.openbankmarvel.data.model.CharacterDTO
import com.juanmacapuano.openbankmarvel.data.model.CharactersDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterService @Inject constructor(private val api: CharacterApiClient) {

    suspend fun getAllCharacters(): CharactersDTO {
        return withContext(Dispatchers.IO) {
            api.getAllCharacters()
        }
    }

    suspend fun getCharacter(id: Int): CharacterDTO {
        return withContext(Dispatchers.IO) {
            api.getCharacter(characterId = id)
        }
    }
}