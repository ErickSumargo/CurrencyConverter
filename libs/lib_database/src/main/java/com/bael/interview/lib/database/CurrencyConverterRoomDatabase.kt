package com.bael.interview.lib.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bael.interview.lib.database.entity.ExchangeRate

/**
 * Created by ErickSumargo on 01/04/21.
 */

@Database(entities = [ExchangeRate::class], version = 1)
abstract class CurrencyConverterRoomDatabase :
    RoomDatabase(),
    CurrencyConverterDatabase
