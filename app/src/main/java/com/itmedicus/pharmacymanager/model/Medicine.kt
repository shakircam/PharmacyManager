package com.itmedicus.pharmacymanager.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "medicine_table",indices = [Index(value = ["medicineName"], unique = true)])
data class Medicine(
    val medicineTitle : String,
    val medicineName : String,
    val generic : String,
    val strength : String,
    val price : Int,
    val picture : String,
    val itemNumber : Int
){
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}

@Entity(tableName = "purchase_table",indices = [Index(value = ["medicineName"], unique = true)])
data class PurchaseMedicine(
    val medicineTitle : String,
    val medicineName : String,
    val generic : String,
    val strength : String,
    val purchasePrice : Int,
    val sellingPrice : Int,
    val picture : String,
    val itemNumber : Int
){
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}

@Entity(tableName = "cart_table",indices = [Index(value = ["medicineName"], unique = true)])
 data class CartMedicine(
    val medicineTitle : String,
    val medicineName : String,
    val generic : String,
    val strength : String,
    val price : Int,
    val picture : String,
    val itemNumber : Int
 ){
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}