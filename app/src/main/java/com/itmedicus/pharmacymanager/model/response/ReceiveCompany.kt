package com.itmedicus.pharmacymanager.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ReceiveCompany(
    val `data`: List<MedicineCompany>,
    val links: Links,
    val meta: Meta
)

@Entity(tableName = "company")
data class MedicineCompany(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val created_at: String,
    val updated_at: String?,
    val deleted_at: String?
)

data class Links(
    val first: String,
    val last: String,
    val next: String,
    val prev: String
)

data class Meta(
    val current_page: Int,
    val from: Int?,
    val last_page: Int,
    val links: List<Link>,
    val path: String,
    val per_page: String,
    val to: Int?,
    val total: Int
)

data class Link(
    val active: Boolean,
    val label: String,
    val url: String
)
