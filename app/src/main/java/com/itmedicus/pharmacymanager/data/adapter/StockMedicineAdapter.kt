package com.itmedicus.pharmacymanager.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itmedicus.pharmacymanager.R
import com.itmedicus.pharmacymanager.model.PurchaseMedicine

class StockMedicineAdapter: RecyclerView.Adapter<StockMedicineAdapter.StockViewHolder> () {
    var list = mutableListOf<PurchaseMedicine>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        return StockViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_stock, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val currentItem = list[position]

        holder.medicineTitle.text = currentItem.medicineTitle
        holder.medicineName.text = currentItem.medicineName
        holder.generic.text = currentItem.strength
        holder.strength.text = currentItem.generic
        holder.purchasePrice.text = "Purchase price: "+currentItem.purchasePrice
        holder.sellingPrice.text = "Selling price: "+currentItem.sellingPrice
        holder.itemNumber.text = "Quantity: "+currentItem.itemNumber

        Glide.with(holder.picture)
            .load(currentItem.picture)
            .into(holder.picture)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val medicineTitle = itemView.findViewById(R.id.capsule) as TextView
        val medicineName = itemView.findViewById(R.id.brand_name_text) as TextView
        val generic = itemView.findViewById(R.id.generic) as TextView
        val strength = itemView.findViewById(R.id.strength) as TextView
        val purchasePrice = itemView.findViewById(R.id.purchaseText) as TextView
        val sellingPrice = itemView.findViewById(R.id.sellingText) as TextView
        val picture = itemView.findViewById(R.id.image) as ImageView
        val itemNumber = itemView.findViewById(R.id.amount) as TextView

    }

    fun setData(medicineList: MutableList<PurchaseMedicine>){
        this.list = medicineList
        notifyDataSetChanged()
    }
}