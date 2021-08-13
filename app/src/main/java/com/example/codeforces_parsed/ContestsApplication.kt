package com.example.codeforces_parsed

import android.app.Application
import com.example.codeforces_parsed.database.ContestListRepository
import com.example.codeforces_parsed.database.ContestsRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ContestsApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { ContestsRoomDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { ContestListRepository(database.contestsDao())}
}