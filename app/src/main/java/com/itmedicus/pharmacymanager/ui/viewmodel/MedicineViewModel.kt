package com.itmedicus.pharmacymanager.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.itmedicus.pharmacymanager.data.local.MedicineDatabase
import com.itmedicus.pharmacymanager.data.repository.MedicineRepository
import com.itmedicus.pharmacymanager.model.CartMedicine
import com.itmedicus.pharmacymanager.model.CartPrice
import com.itmedicus.pharmacymanager.model.Medicine
import com.itmedicus.pharmacymanager.model.PurchaseMedicine
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
    val getCartPrice: LiveData<List<CartPrice>> = repository.getCartPrice

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

    fun insertMedicineToPurchase(purchaseMedicine: PurchaseMedicine){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMedicineToPurchase(purchaseMedicine)
        }
    }

    fun deleteMedicineFromCart(cartMedicine: CartMedicine){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMedicineFromCart(cartMedicine)
        }
    }

    fun searchMedicine(searchQuery : String): LiveData<MutableList<Medicine>>{
        return repository.searchMedicine(searchQuery)
    }

    fun searchMedicineFromPurchase(searchQuery : String): LiveData<MutableList<PurchaseMedicine>>{
        return repository.searchMedicineFromPurchase(searchQuery)
    }

}