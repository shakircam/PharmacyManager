package com.itmedicus.pharmacymanager.utils

import com.itmedicus.pharmacymanager.model.PurchaseCart

interface ClickListener {
    fun onItemPurchaseToCart(purchaseCart: PurchaseCart)

}