package com.bael.interview.lib.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by ErickSumargo on 01/04/21.
 */

@Entity(tableName = "exchange_rate")
data class ExchangeRate(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "rate")
    val rate: Double,
    @ColumnInfo(name = "timestamp")
    val timestamp: Long
)
