package com.itmedicus.pharmacymanager.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.itmedicus.pharmacymanager.data.local.MedicineDatabase
import com.itmedicus.pharmacymanager.data.repository.MedicineRepository
import com.itmedicus.pharmacymanager.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicineViewModel(application: Application) : AndroidViewModel(application) {

    private val medicineDao = MedicineDatabase.getDatabase(
        application
    ).medicineDao()

    private val repository: MedicineRepository = MedicineRepository(medicineDao)

    val readMedicineData: LiveData<MutableList<Medicine>> = repository.readMedicineData
    val readMedicineFromCart: LiveData<MutableList<CartMedicine>> = repository.readMedicineFromCart
    val readMedicineFromPurchase: LiveData<MutableList<PurchaseMedicine>> = repository.readMedicineFromPurchase
    val readMedicineFromPurchaseCart: LiveData<MutableList<PurchaseCart>> = repository.readMedicineFromPurchaseCart

    val sendDataToStockList: LiveData<MutableList<PurchaseMedicine>> = repository.sendDataToStockList
    val getCartPrice: LiveData<List<CartPrice>> = repository.getCartPrice
    val getPurchasePrice: LiveData<List<PurchasePrice>> = repository.getPurchasePrice
    val getRowNumberFromPurchaseCart : LiveData<Int> = repository.getRowNumberFromPurchaseCart
    val getRowNumberFromCart : LiveData<Int> = repository.getRowNumberFromCart


    fun insertMedicineData(medicine: ArrayList<Medicine>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMedicineData(medicine)
        }
    }

    fun insertMedicineToCart(cartMedicine: CartMedicine){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMedicineToCart(cartMedicine)
        }
    }

    fun insertMedicineToPurchase(purchaseMedicine: MutableList<PurchaseMedicine>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMedicineToPurchase(purchaseMedicine)
        }
    }

    fun insertMedicineToPurchaseCart(purchaseCart: PurchaseCart){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMedicineToPurchaseCart(purchaseCart)
        }
    }

    fun deleteMedicineFromCart(cartMedicine: CartMedicine){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMedicineFromCart(cartMedicine)
        }
    }

    fun deleteMedicineFromPurchaseCart(purchaseCart: MutableList<PurchaseCart>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMedicineFromPurchaseCart(purchaseCart)
        }
    }

    fun deleteSingleItemFromPurchaseCart(purchaseCart: PurchaseCart){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSingleItemFromPurchaseCart(purchaseCart)
        }
    }


    fun searchMedicine(searchQuery : String): LiveData<MutableList<Medicine>>{
        return repository.searchMedicine(searchQuery)
    }

    fun searchMedicineFromPurchase(searchQuery : String): LiveData<MutableList<PurchaseMedicine>>{
        return repository.searchMedicineFromPurchase(searchQuery)
    }

}