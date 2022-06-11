package com.juanmacapuano.openbankmarvel.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.juanmacapuano.openbankmarvel.domain.model.CharacterModel

@Entity(tableName = "character_table")
data class CharacterEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "thumbnailExt") val thumbnailExt: String
)

fun CharacterModel.toDatabase() =
    CharacterEntity(
        name = name,
        description = description,
        thumbnail = thumbnail,
        thumbnailExt = thumbnailExt
    )



