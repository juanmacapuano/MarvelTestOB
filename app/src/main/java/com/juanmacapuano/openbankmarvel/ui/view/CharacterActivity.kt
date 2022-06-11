package com.juanmacapuano.openbankmarvel.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.juanmacapuano.openbankmarvel.R
import com.juanmacapuano.openbankmarvel.databinding.ActivityCharacterBinding
import com.juanmacapuano.openbankmarvel.ui.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterBinding
    private val characterViewModel: CharacterViewModel by viewModels()
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent != null) {
            id = intent.getIntExtra("id", 0)
            characterViewModel.getCharacter(id)

            characterViewModel.listCharacter.observe(this, {
                val character = it[0]
                binding.tvCharacterCharacterName.text = character.name
                binding.tvCharacterCharacterDesc.text = character.description
                Glide.with(this)
                    .load(character.thumbnail + "." + character.thumbnailExt)
                    .into(binding.ivCharacterCharacterImage)

            })
        }
    }
}