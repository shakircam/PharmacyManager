package com.itmedicus.pharmacymanager.model.response

data class RegistrationResponse(
    val key: String,
    val message: Message,
    val success: String
)

data class Message(
    val body: List<String>
)
