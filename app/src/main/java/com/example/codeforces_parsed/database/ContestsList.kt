package com.example.codeforces_parsed.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.format.DateTimeFormatter

@Entity(tableName="cf_contests_app")
data class ContestList (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "duration") val duration: String?,
    @ColumnInfo(name = "startTime") val startTime : String?
)
