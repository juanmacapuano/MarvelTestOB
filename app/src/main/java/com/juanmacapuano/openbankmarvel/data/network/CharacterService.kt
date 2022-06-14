package com.juanmacapuano.openbankmarvel.data.network

import com.juanmacapuano.openbankmarvel.data.model.CharacterDTO
import com.juanmacapuano.openbankmarvel.data.model.CharactersDTO
import com.juanmacapuano.openbankmarvel.domain.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterService @Inject constructor(private val api: CharacterApiClient) {

    suspend fun getAllCharacters(): List<CharacterModel>? {
        return withContext(Dispatchers.IO) {
            val charactersDTO = api.getAllCharacters()
            charactersDTO.data?.results?.map { it.toCharacter() }
        }
    }

    suspend fun getCharacter(id: Int): List<CharacterModel> {
        return withContext(Dispatchers.IO) {
            api.getCharacter(characterId = id).data.results.map { it.toCharacter() }
        }
    }
}