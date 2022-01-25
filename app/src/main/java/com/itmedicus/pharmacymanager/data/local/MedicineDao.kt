package com.itmedicus.pharmacymanager.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.itmedicus.pharmacymanager.model.CartMedicine
import com.itmedicus.pharmacymanager.model.CartPrice
import com.itmedicus.pharmacymanager.model.Medicine


@Dao
interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMedicineData(medicine: ArrayList<Medicine>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicineToCart(cartMedicine: CartMedicine)

    @Query("SELECT * FROM medicine_table ORDER BY id ASC  ")
    fun readMedicineData(): LiveData<MutableList<Medicine>>

    @Query("SELECT * FROM cart_table ORDER BY id ASC  ")
    fun readMedicineFromCart(): LiveData<MutableList<CartMedicine>>

    @Query("SELECT * FROM cart_table ")
    fun getCartPrice(): LiveData<List<CartPrice>>

    @Query("UPDATE cart_table SET itemNumber = itemNumber+:itemNumber,price = price+:price   WHERE medicineName = :medicineName")
    fun updateQuantity(medicineName : String, itemNumber : Int, price : Int)

    @Delete
    suspend fun deleteMedicine(medicine: Medicine)

    @Delete
    suspend fun deleteMedicineFromCart(cartMedicine: CartMedicine)

    @Query("SELECT * FROM medicine_table WHERE medicineName LIKE :searchQuery OR generic LIKE :searchQuery")
    fun searchMedicine(searchQuery: String): LiveData<MutableList<Medicine>>

}