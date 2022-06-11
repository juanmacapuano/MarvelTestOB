package com.juanmacapuano.openbankmarvel.data

import android.util.Log
import com.juanmacapuano.openbankmarvel.data.database.dao.CharacterDao
import com.juanmacapuano.openbankmarvel.data.database.entities.CharacterEntity
import com.juanmacapuano.openbankmarvel.data.model.CharacterDTO
import com.juanmacapuano.openbankmarvel.data.model.CharactersDTO
import com.juanmacapuano.openbankmarvel.data.network.CharacterService
import com.juanmacapuano.openbankmarvel.domain.model.CharacterModel
import com.juanmacapuano.openbankmarvel.domain.model.toDomain
import com.juanmacapuano.openbankmarvel.domain.repository.CharacterRepository
import javax.inject.Inject


class CharactersRepositoryImpl @Inject constructor(
    private val api: CharacterService,
    private val characterDao: CharacterDao
): CharacterRepository {

    override suspend fun getCharacterFromApi(id: Int): CharacterDTO {
        return api.getCharacter(id = id)
    }

    override suspend fun getAllCharacters(): CharactersDTO {
        return api.getAllCharacters()
    }

    suspend fun getAllCharactersFromDatabase(): List<CharacterModel> {
        val response: List<CharacterEntity> = characterDao.getAllCharacters()
        return response.map { it.toDomain() }
    }

    suspend fun insertCharacters(characters: List<CharacterEntity>) {
        characterDao.insertAll(characters)
    }

    suspend fun clearCharacters() {
        characterDao.deleteAll()
    }
}