package com.itmedicus.pharmacymanager.model.credential

data class UserCredential(
    val name: String,
    val company:String,
    val phone:String,
    val email:String,
    val password:String,
    val password_confirmation:String,
    val occupation:Int,
    val address: String
)
