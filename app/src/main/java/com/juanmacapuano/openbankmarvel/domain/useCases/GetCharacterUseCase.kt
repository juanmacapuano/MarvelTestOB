package com.juanmacapuano.openbankmarvel.domain.useCases

import com.juanmacapuano.openbankmarvel.data.CharactersRepositoryImpl
import com.juanmacapuano.openbankmarvel.data.database.entities.toDatabase
import com.juanmacapuano.openbankmarvel.domain.model.CharacterModel
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repositoryImpl: CharactersRepositoryImpl
) {

    suspend operator fun invoke(id: Int): List<CharacterModel> {
        val listCharacter = repositoryImpl.getCharacterFromApi(id)

        return if (listCharacter.isNotEmpty()) {
            repositoryImpl.clearCharacters()
            repositoryImpl.insertCharacters(listCharacter.map { it.toDatabase() })
            listCharacter
        } else {
            repositoryImpl.getAllCharactersFromDatabase()
        }
    }
}