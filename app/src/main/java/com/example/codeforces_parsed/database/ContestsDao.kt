package com.example.codeforces_parsed.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContestsDao {
    @Query("SELECT * FROM cf_contests_app")
    fun getAll(): Flow<List<ContestList>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contestList: ContestList)

}