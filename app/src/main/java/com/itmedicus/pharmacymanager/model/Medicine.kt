package com.itmedicus.pharmacymanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine_table")
data class Medicine(
    val medicineTitle : String,
    val medicineName : String,
    val generic : String,
    val strength : String,
    val price : Int,
    val picture : String
){
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}
