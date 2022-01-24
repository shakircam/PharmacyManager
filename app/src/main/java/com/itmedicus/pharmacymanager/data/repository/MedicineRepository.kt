package com.itmedicus.pharmacymanager.data.repository

import androidx.lifecycle.LiveData
import com.itmedicus.pharmacymanager.data.local.MedicineDao
import com.itmedicus.pharmacymanager.model.Medicine

class MedicineRepository (private val medicineDao: MedicineDao){

    val readMedicineData: LiveData<MutableList<Medicine>> = medicineDao.readMedicineData()


    suspend fun  insertMedicineData(medicine: ArrayList<Medicine>){
        medicineDao.insertMedicineData(medicine)
    }

    fun searchMedicine(searchQuery: String): LiveData<MutableList<Medicine>>{
        return medicineDao.searchMedicine(searchQuery)
    }
}