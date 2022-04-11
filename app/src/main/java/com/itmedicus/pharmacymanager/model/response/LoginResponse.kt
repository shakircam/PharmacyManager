package com.itmedicus.pharmacymanager.model.response

data class LoginResponse(
    val address: String,
    val company: String,
    val email: String,
    val id: Int,
    val key: String,
    val name: String,
    val occupation: Int,
    val phone: String,
    val success: String
)
