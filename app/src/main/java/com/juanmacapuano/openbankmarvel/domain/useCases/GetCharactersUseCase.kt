package com.juanmacapuano.openbankmarvel.domain.useCases

import android.util.Log
import com.juanmacapuano.openbankmarvel.data.CharactersRepositoryImpl
import com.juanmacapuano.openbankmarvel.data.database.entities.toDatabase
import com.juanmacapuano.openbankmarvel.domain.model.CharacterModel
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repositoryImpl: CharactersRepositoryImpl
) {

    suspend operator fun invoke(): List<CharacterModel> {
        val listCharacter = repositoryImpl.getAllCharacters() ?: emptyList()

        return if (listCharacter.isNotEmpty()) {
            repositoryImpl.clearCharacters()
            repositoryImpl.insertCharacters(listCharacter.map { it.toDatabase() })
            listCharacter
        } else {
            val listResponse = repositoryImpl.getAllCharactersFromDatabase()
            if (listResponse.isNotEmpty()) listResponse
            else {
                emptyList()
            }
        }
    }
}