package com.juanmacapuano.openbankmarvel.utils

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanmacapuano.openbankmarvel.databinding.RecyclerviewItemBinding
import com.juanmacapuano.openbankmarvel.domain.model.CharacterModel
import com.juanmacapuano.openbankmarvel.ui.view.CharacterActivity

class CharacterListAdapter(private val context: Context) :
    ListAdapter<CharacterModel, CharacterListAdapter.CharacterViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<CharacterModel>() {
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterListAdapter.CharacterViewHolder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context))
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterListAdapter.CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    inner class CharacterViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: CharacterModel) {
            binding.tvCharacterName.text = character.name
            Glide.with(context)
                .load(character.thumbnail + "." + character.thumbnailExt)
                .into(binding.ivCharacter)
            binding.root.setOnClickListener {
                val intent = Intent(context, CharacterActivity::class.java)
                intent.putExtra("id", character.id)
                context.startActivity(intent)
            }
            //binding.executePendingBindings()
        }
    }
}