package com.example.codeforces_parsed.database

import androidx.room.*

@Dao
interface ContestsDao {
    @Query("SELECT * FROM cf_contests_app")
    fun getAll(): List<ContestList>

}