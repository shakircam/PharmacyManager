package com.itmedicus.pharmacymanager.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itmedicus.pharmacymanager.model.CartMedicine
import com.itmedicus.pharmacymanager.model.Medicine
import com.itmedicus.pharmacymanager.model.PurchaseMedicine


@Database(entities = [Medicine::class,CartMedicine::class,PurchaseMedicine::class],version = 1, exportSchema = false)
abstract class MedicineDatabase : RoomDatabase(){
    abstract fun medicineDao(): MedicineDao

    companion object {
        @Volatile
        private var INSTANCE: MedicineDatabase? = null

        fun getDatabase(context: Context): MedicineDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MedicineDatabase::class.java, "medicine_database"
            ).allowMainThreadQueries().build()
    }

}