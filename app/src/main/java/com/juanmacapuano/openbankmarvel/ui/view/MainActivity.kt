package com.juanmacapuano.openbankmarvel.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanmacapuano.openbankmarvel.R
import com.juanmacapuano.openbankmarvel.core.extension.visible
import com.juanmacapuano.openbankmarvel.databinding.ActivityMainBinding
import com.juanmacapuano.openbankmarvel.ui.viewmodel.CharactersViewModel
import com.juanmacapuano.openbankmarvel.utils.CharacterListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var layoutManager: GridLayoutManager
    private val charactersViewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerViewSetup()
        setObservers()
    }

    private fun setObservers() {
        charactersViewModel.listCharacters.observe(this, { listCharacters ->
            characterListAdapter.submitList(listCharacters)
        })

        charactersViewModel.showProgressBar.observe(this, {
            binding.pbProgress.isVisible = it
        })

        charactersViewModel.showTextEmptyList.observe(this, {
            binding.ivEmptyView.isVisible = it
        })
    }

    private fun recyclerViewSetup() {
        layoutManager = GridLayoutManager(this, 2)
        binding.rvMovieList.layoutManager = layoutManager
        characterListAdapter = CharacterListAdapter(this)
        //charactersViewModel.onCreate()
        binding.rvMovieList.adapter = characterListAdapter
    }

    override fun onStart() {
        super.onStart()
        charactersViewModel.onCreate()
    }
}