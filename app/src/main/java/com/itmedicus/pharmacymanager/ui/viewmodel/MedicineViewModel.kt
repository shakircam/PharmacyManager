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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicineViewModel(application: Application) : AndroidViewModel(application) {

    private val medicineDao = MedicineDatabase.getDatabase(
        application
    ).medicineDao()


    private val repository: MedicineRepository = MedicineRepository(medicineDao)
    val readMedicineData: LiveData<MutableList<Medicine>> = repository.readMedicineData
    val readMedicineFromCart: LiveData<MutableList<CartMedicine>> = repository.readMedicineFromCart
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

    fun deleteMedicineFromCart(cartMedicine: CartMedicine){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMedicineFromCart(cartMedicine)
        }
    }

    fun searchMedicine(searchQuery : String): LiveData<MutableList<Medicine>>{
        return repository.searchMedicine(searchQuery)
    }

}