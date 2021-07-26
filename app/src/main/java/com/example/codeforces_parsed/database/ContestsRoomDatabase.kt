package com.example.codeforces_parsed.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(ContestList::class), version = 1, exportSchema = false) //feel like exportSchema should be true
public abstract class ContestsRoomDatabase : RoomDatabase(){
    abstract fun contestsDao() : ContestsDao

    // Singleton prevents multiple instances of database opening at the
    // same time.
    companion object {
        @Volatile
        private var INSTANCE : ContestsRoomDatabase? = null

        fun getDatabase(context : Context) : ContestsRoomDatabase{
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContestsRoomDatabase::class.java,
                    "sqlite_python"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}