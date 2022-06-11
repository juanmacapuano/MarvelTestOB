package com.juanmacapuano.openbankmarvel.data.model

import com.juanmacapuano.openbankmarvel.domain.model.CharacterModel

data class CharactersDTO(
    val attributionHTML: String = "",
    val attributionText: String = "",
    val code: Int = 0,
    val copyright: String = "",
    val `data`: Data? = null,
    val etag: String = "",
    val status: String = ""
)

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)

data class Result(
    val description: String,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val thumbnail: Thumbnail
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

data class Thumbnail(
    val extension: String,
    val path: String
)