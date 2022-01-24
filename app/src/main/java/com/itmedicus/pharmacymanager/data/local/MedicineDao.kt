package com.itmedicus.pharmacymanager.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.itmedicus.pharmacymanager.model.Medicine


@Dao
interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMedicineData(userData: ArrayList<Medicine>)

    @Query("SELECT * FROM medicine_table ORDER BY id ASC  ")
    fun readMedicineData(): LiveData<MutableList<Medicine>>

    @Delete
    suspend fun deleteAlarm(medicine: Medicine)

    @Query("SELECT * FROM medicine_table WHERE medicineName LIKE :searchQuery OR generic LIKE :searchQuery")
    fun searchMedicine(searchQuery: String): LiveData<MutableList<Medicine>>

}