package com.example.codeforces_parsed.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope


// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(ContestList::class), version = 1, exportSchema = false) //feel like exportSchema should be true
abstract class ContestsRoomDatabase : RoomDatabase(){
    abstract fun contestsDao() : ContestsDao

    private class ContestsDatabaseCallback(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }
    }

    // Singleton prevents multiple instances of database opening at the
    // same time.
    companion object {
        @Volatile
        private var INSTANCE : ContestsRoomDatabase? = null

        fun getDatabase(
            context : Context,
            scope: CoroutineScope
        ) : ContestsRoomDatabase{
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContestsRoomDatabase::class.java,
                    "sqlite_python"
                )
                    .addCallback(ContestsDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}