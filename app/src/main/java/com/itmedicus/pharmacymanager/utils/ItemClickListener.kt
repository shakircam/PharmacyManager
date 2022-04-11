package com.itmedicus.pharmacymanager.utils

import com.itmedicus.pharmacymanager.model.CartMedicine

interface ItemClickListener {

    fun onItemSend(cartMedicine: CartMedicine)
    fun onItemDelete(cartMedicine: CartMedicine,position:Int)
}