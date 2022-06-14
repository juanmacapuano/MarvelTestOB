package com.juanmacapuano.openbankmarvel.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanmacapuano.openbankmarvel.domain.model.CharacterModel
import com.juanmacapuano.openbankmarvel.domain.useCases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _listCharacters = MutableLiveData<List<CharacterModel>>()
    val listCharacters: LiveData<List<CharacterModel>> = _listCharacters

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar: LiveData<Boolean> = _showProgressBar

    private val _showTextEmptyList = MutableLiveData<Boolean>()
    val showTextEmptyList: LiveData<Boolean> = _showTextEmptyList

    fun onCreate() {
        viewModelScope.launch(Dispatchers.IO) {
            _showProgressBar.postValue(true)
            val result = getCharactersUseCase()
            if (!result.isNullOrEmpty()) {
                _listCharacters.postValue(result)
                _showProgressBar.postValue(false)
                _showTextEmptyList.postValue(false)
            } else {
                _listCharacters.postValue(emptyList())
                _showTextEmptyList.postValue(true)
            }
        }
    }
}