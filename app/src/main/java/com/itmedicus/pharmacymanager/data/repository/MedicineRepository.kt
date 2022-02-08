package com.itmedicus.pharmacymanager.data.repository

import androidx.lifecycle.LiveData
import com.itmedicus.pharmacymanager.data.local.MedicineDao
import com.itmedicus.pharmacymanager.model.*

class MedicineRepository (private val medicineDao: MedicineDao){

    val readMedicineData: LiveData<MutableList<Medicine>> = medicineDao.readMedicineData()
    val readMedicineFromCart: LiveData<MutableList<CartMedicine>> = medicineDao.readMedicineFromCart()
    val readMedicineFromPurchase: LiveData<MutableList<PurchaseMedicine>> = medicineDao.readMedicineFromPurchase()
    val readMedicineFromPurchaseCart: LiveData<MutableList<PurchaseCart>> = medicineDao.readMedicineFromPurchaseCart()

    val sendDataToStockList: LiveData<MutableList<PurchaseMedicine>> = medicineDao.sendDataToStockList()
    val getCartPrice: LiveData<List<CartPrice>> = medicineDao.getCartPrice()
    val getPurchasePrice: LiveData<List<PurchasePrice>> = medicineDao.getPurchasePrice()
    val getRowNumberFromPurchaseCart : LiveData<Int> = medicineDao.getRowNumberFromPurchaseCart()
    val getRowNumberFromCart : LiveData<Int> = medicineDao.getRowNumberFromCart()

    suspend fun  insertMedicineData(medicine: ArrayList<Medicine>){
        medicineDao.insertMedicineData(medicine)
    }

    suspend fun  insertMedicineToCart(cartMedicine: CartMedicine){
        medicineDao.insertMedicineToCart(cartMedicine)
    }

    suspend fun  insertMedicineToPurchase(purchaseMedicine: MutableList<PurchaseMedicine>){
        medicineDao.insertMedicineToPurchase(purchaseMedicine)
    }

    suspend fun  insertMedicineToPurchaseCart(purchaseCart: PurchaseCart){
        medicineDao.insertMedicineToPurchaseCart(purchaseCart)
    }

    suspend fun  deleteMedicineFromCart(cartMedicine: CartMedicine){
        medicineDao.deleteMedicineFromCart(cartMedicine)
    }


    suspend fun  deleteMedicineFromPurchaseCart(purchaseCart: MutableList<PurchaseCart>){
        medicineDao.deleteMedicineFromPurchaseCart(purchaseCart)
    }

    suspend fun  deleteSingleItemFromPurchaseCart(purchaseCart: PurchaseCart){
        medicineDao.deleteSingleItemFromPurchaseCart(purchaseCart)
    }


    fun searchMedicine(searchQuery: String): LiveData<MutableList<Medicine>>{
        return medicineDao.searchMedicine(searchQuery)
    }

    fun searchMedicineFromPurchase(searchQuery: String): LiveData<MutableList<PurchaseMedicine>>{
        return medicineDao.searchMedicineFromPurchase(searchQuery)
    }
}