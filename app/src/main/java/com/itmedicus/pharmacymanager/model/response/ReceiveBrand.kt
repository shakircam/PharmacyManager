package com.itmedicus.pharmacymanager.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ReceiveBrand(
    val `data`: List<Brand>,
    val links: BrandLinks,
    val meta: BrandMeta
)

@Entity(tableName = "brand")
data class Brand(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val brand_name: String,
    val generic_id: Int,
    val company_id: Int,
    val form: String,
    val strength: String,
    val price: String,
    val packsize: String,
    val created_at: String,
    val updated_at: String?,
    val deleted_at: String?

)

data class BrandMeta(
    val current_page: Int,
    val from: Int?,
    val last_page: Int,
    val links: List<BrandLink>,
    val path: String,
    val per_page: String,
    val to: Int?,
    val total: Int
)

data class BrandLink(
    val active: Boolean,
    val label: String,
    val url: Any
)

data class BrandLinks(
    val first: String,
    val last: String,
    val next: String,
    val prev: Any
)
