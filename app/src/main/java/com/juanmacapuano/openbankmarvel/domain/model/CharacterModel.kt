package com.juanmacapuano.openbankmarvel.domain.model

import com.juanmacapuano.openbankmarvel.data.database.entities.CharacterEntity
import com.juanmacapuano.openbankmarvel.data.model.CharacterDTO
import com.juanmacapuano.openbankmarvel.data.model.ResultCharacter

data class CharacterModel(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String,
    val thumbnailExt: String,
)

fun CharacterEntity.toDomain() = CharacterModel(
    id = id,
    name = name,
    description = description,
    thumbnail = thumbnail,
    thumbnailExt = thumbnailExt
)

