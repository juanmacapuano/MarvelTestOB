package com.juanmacapuano.openbankmarvel.domain

import com.juanmacapuano.openbankmarvel.data.CharactersRepositoryImpl
import com.juanmacapuano.openbankmarvel.data.model.CharactersDTO
import com.juanmacapuano.openbankmarvel.domain.model.CharacterModel
import com.juanmacapuano.openbankmarvel.domain.repository.CharacterRepository
import com.juanmacapuano.openbankmarvel.domain.useCases.GetCharactersUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCharactersUseCaseTest {

    @RelaxedMockK
    private lateinit var characterRepository: CharactersRepositoryImpl

    @RelaxedMockK
    lateinit var getCharactersUseCase: GetCharactersUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getCharactersUseCase = GetCharactersUseCase(characterRepository)
    }

    @Test
    fun `when api doesnt return anything then load data from database`() = runBlocking {

        coEvery { characterRepository.getAllCharacters() } returns emptyList()
        getCharactersUseCase()
        coVerify(exactly = 1) { characterRepository.getAllCharactersFromDatabase() }

    }

    @Test
    fun `when api return anything then get values from api`() = runBlocking {
        val characters = listOf(CharacterModel(0,"test","description","thumb", "thumext"))
        coEvery { characterRepository.getAllCharacters() } returns characters

        val response = getCharactersUseCase()

        coVerify(exactly = 1) { characterRepository.clearCharacters() }
        coVerify(exactly = 1) { characterRepository.insertCharacters(any()) }
        coVerify(exactly = 0) { characterRepository.getAllCharactersFromDatabase() }
        assert(characters == response)
    }

    @Test
    fun `when database is empty then return empty`() = runBlocking {

        coEvery { characterRepository.getAllCharactersFromDatabase() } returns emptyList()

        val response = getCharactersUseCase()
        assert(response == emptyList<CharacterModel>())
    }

    @Test
    fun `when database is not empty then return a list`() = runBlocking {
        val characterListItem =
            listOf(CharacterModel(1, "test", "description", "thumbnail", "thumbnailExt"))

        coEvery { characterRepository.getAllCharactersFromDatabase() } returns characterListItem

        val response = getCharactersUseCase()

        assert(response.first() == characterListItem.first())
    }
}