package com.itmedicus.pharmacymanager.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.itmedicus.pharmacymanager.model.*


@Dao
interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMedicineData(medicine: ArrayList<Medicine>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicineToCart(cartMedicine: CartMedicine)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicineToPurchase(purchaseMedicine: MutableList<PurchaseMedicine>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicineToPurchaseCart(purchaseCart: PurchaseCart)

    @Query("SELECT * FROM medicine_table ORDER BY id ASC  ")
    fun readMedicineData(): LiveData<MutableList<Medicine>>

    @Query("SELECT * FROM cart_table ORDER BY id ASC  ")
    fun readMedicineFromCart(): LiveData<MutableList<CartMedicine>>

    @Query("SELECT * FROM purchase_table ORDER BY id ASC  ")
    fun readMedicineFromPurchase(): LiveData<MutableList<PurchaseMedicine>>

    @Query("SELECT * FROM purchase_cart_table ORDER BY id ASC  ")
    fun readMedicineFromPurchaseCart(): LiveData<MutableList<PurchaseCart>>

    @Query("SELECT COUNT(*) FROM purchase_cart_table")
    fun getRowNumberFromPurchaseCart():LiveData<Int>

    @Query("SELECT COUNT(*) FROM cart_table")
    fun getRowNumberFromCart():LiveData<Int>

    @Query("SELECT * FROM purchase_cart_table ORDER BY id ASC  ")
    fun sendDataToStockList(): LiveData<MutableList<PurchaseMedicine>>

    @Query("SELECT * FROM cart_table ")
    fun getCartPrice(): LiveData<List<CartPrice>>

    @Query("SELECT * FROM purchase_cart_table ")
    fun getPurchasePrice(): LiveData<List<PurchasePrice>>

    @Query("UPDATE cart_table SET itemNumber = itemNumber+:itemNumber,price = price+:price   WHERE medicineName = :medicineName")
    fun updateQuantityInCart(medicineName : String, itemNumber : Int, price : Int)

    @Query("UPDATE purchase_table SET itemNumber = itemNumber+:itemNumber  WHERE medicineName = :medicineName")
    fun updateQuantityInStock(medicineName : String, itemNumber : Int)

    @Delete
    suspend fun deleteMedicine(medicine: Medicine)

    @Delete
    suspend fun deleteMedicineFromCart(cartMedicine: CartMedicine)

    @Delete
    suspend fun deleteMedicineFromPurchaseCart(purchaseCart: MutableList<PurchaseCart>)

    @Delete
    suspend fun deleteSingleItemFromPurchaseCart(purchaseCart: PurchaseCart)

    @Query("SELECT * FROM medicine_table WHERE medicineName LIKE :searchQuery OR generic LIKE :searchQuery")
    fun searchMedicine(searchQuery: String): LiveData<MutableList<Medicine>>

    @Query("SELECT * FROM purchase_table WHERE medicineName LIKE :searchQuery OR generic LIKE :searchQuery")
    fun searchMedicineFromPurchase(searchQuery: String): LiveData<MutableList<PurchaseMedicine>>

}