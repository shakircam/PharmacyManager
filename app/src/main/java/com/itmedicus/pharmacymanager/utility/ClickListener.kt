package com.itmedicus.pharmacymanager.utility

import com.itmedicus.pharmacymanager.model.CartMedicine
import com.itmedicus.pharmacymanager.model.PurchaseMedicine

interface ClickListener {
    fun onItemPurchase(purchaseMedicine: PurchaseMedicine)
}