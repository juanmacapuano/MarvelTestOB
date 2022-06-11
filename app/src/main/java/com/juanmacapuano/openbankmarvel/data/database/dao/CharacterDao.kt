package com.juanmacapuano.openbankmarvel.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juanmacapuano.openbankmarvel.data.database.entities.CharacterEntity

@Dao
interface CharacterDao {

   @Query("SELECT * FROM character_table")
   suspend fun getAllCharacters(): List<CharacterEntity>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertAll(character:List<CharacterEntity>)

   @Query("DELETE FROM character_table")
   suspend fun deleteAll()
}