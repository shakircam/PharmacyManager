package com.itmedicus.pharmacymanager.data.repository

import androidx.lifecycle.LiveData
import com.itmedicus.pharmacymanager.data.local.MedicineDao
import com.itmedicus.pharmacymanager.model.CartMedicine
import com.itmedicus.pharmacymanager.model.CartPrice
import com.itmedicus.pharmacymanager.model.Medicine
import com.itmedicus.pharmacymanager.model.PurchaseMedicine

class MedicineRepository (private val medicineDao: MedicineDao){

    val readMedicineData: LiveData<MutableList<Medicine>> = medicineDao.readMedicineData()
    val readMedicineFromCart: LiveData<MutableList<CartMedicine>> = medicineDao.readMedicineFromCart()
    val readMedicineFromPurchase: LiveData<MutableList<PurchaseMedicine>> = medicineDao.readMedicineFromPurchase()
    val getCartPrice: LiveData<List<CartPrice>> = medicineDao.getCartPrice()

    suspend fun  insertMedicineData(medicine: ArrayList<Medicine>){
        medicineDao.insertMedicineData(medicine)
    }

    suspend fun  insertMedicineToCart(cartMedicine: CartMedicine){
        medicineDao.insertMedicineToCart(cartMedicine)
    }

    suspend fun  insertMedicineToPurchase(purchaseMedicine: PurchaseMedicine){
        medicineDao.insertMedicineToPurchase(purchaseMedicine)
    }

    suspend fun  deleteMedicineFromCart(cartMedicine: CartMedicine){
        medicineDao.deleteMedicineFromCart(cartMedicine)
    }

    fun searchMedicine(searchQuery: String): LiveData<MutableList<Medicine>>{
        return medicineDao.searchMedicine(searchQuery)
    }

    fun searchMedicineFromPurchase(searchQuery: String): LiveData<MutableList<PurchaseMedicine>>{
        return medicineDao.searchMedicineFromPurchase(searchQuery)
    }
}