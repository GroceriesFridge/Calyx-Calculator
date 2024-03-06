package com.thalajaat.calyxcalculator.data.datasources.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DropDownRatesTable")
data class DropDownRateEntity(
    @PrimaryKey()
    var id: String,
    val start:String,
    val index:Int,
    val rate:Double,
    val timestamp:String,
    val end:String,
    val isPinned:Boolean = false,
)