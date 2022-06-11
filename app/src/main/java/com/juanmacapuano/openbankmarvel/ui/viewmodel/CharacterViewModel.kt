package com.juanmacapuano.openbankmarvel.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanmacapuano.openbankmarvel.domain.model.CharacterModel
import com.juanmacapuano.openbankmarvel.domain.useCases.GetCharacterUseCase
import com.juanmacapuano.openbankmarvel.domain.useCases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    private val _listCharacter = MutableLiveData<List<CharacterModel>>()
    val listCharacter: LiveData<List<CharacterModel>> = _listCharacter

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar: LiveData<Boolean> = _showProgressBar

    fun getCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _showProgressBar.postValue(true)

            val result = getCharacterUseCase(id)

            if (!result.isNullOrEmpty()) {
                _listCharacter.postValue(result)
                _showProgressBar.postValue(false)
            }
        }

    }
}