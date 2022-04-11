package com.itmedicus.pharmacymanager.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ReceiveGeneric(
    val `data`: List<Generic>,
    val links: GenericLinks,
    val meta: GenericMeta
)

@Entity(tableName = "generic")
data class Generic(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val generic_name: String?,
    val created_at: String,
    val updated_at: String?,
    val deleted_at: String?
)

data class GenericLinks(
    val first: String,
    val last: String,
    val next: String,
    val prev: String
)

data class GenericMeta(
    val current_page: Int,
    val from: Int?,
    val last_page: Int,
    val links: List<GenericLink>,
    val path: String,
    val per_page: String,
    val to: Int?,
    val total: Int
)

data class GenericLink(
    val active: Boolean,
    val label: String,
    val url: Any
)
