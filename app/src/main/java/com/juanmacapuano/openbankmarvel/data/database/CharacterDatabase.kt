package com.juanmacapuano.openbankmarvel.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juanmacapuano.openbankmarvel.data.database.dao.CharacterDao
import com.juanmacapuano.openbankmarvel.data.database.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class CharacterDatabase: RoomDatabase() {

    abstract fun getDao(): CharacterDao
}