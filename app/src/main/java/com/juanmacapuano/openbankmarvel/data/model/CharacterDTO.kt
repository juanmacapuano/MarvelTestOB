package com.juanmacapuano.openbankmarvel.data.model

import com.juanmacapuano.openbankmarvel.domain.model.CharacterModel

data class CharacterDTO(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: DataCharacter,
    val etag: String,
    val status: String
)

data class DataCharacter(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultCharacter>,
    val total: Int
)

data class ResultCharacter(
    val description: String,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val thumbnail: ThumbnailCharacter
) {
    fun toCharacter(): CharacterModel {
        return CharacterModel(
            id=id,
            name=name,
            description=description,
            thumbnail = thumbnail.path,
            thumbnailExt=thumbnail.extension
        )
    }
}

data class ThumbnailCharacter(
    val extension: String,
    val path: String
)