package com.itmedicus.pharmacymanager.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itmedicus.pharmacymanager.R
import com.itmedicus.pharmacymanager.model.Medicine

class MedicineAdapter : RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> ()  {
    var list = mutableListOf<Medicine>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {

        return MedicineViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {

        val currentItem = list[position]
        holder.medicineTitle.text = currentItem.medicineTitle
        holder.medicineName.text = currentItem.medicineName
        holder.generic.text = currentItem.strength
        holder.strength.text = currentItem.generic
        holder.price.text = currentItem.price.toString()

        holder.addButton.setOnClickListener {  }
        holder.minusButton.setOnClickListener {  }
        holder.addToCart.setOnClickListener {  }

        Glide.with(holder.picture)
            .load(currentItem.picture)
            .into(holder.picture)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MedicineViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val medicineTitle = itemView.findViewById(R.id.capsule) as TextView
        val medicineName = itemView.findViewById(R.id.brand_name_text) as TextView
        val generic = itemView.findViewById(R.id.generic) as TextView
        val strength = itemView.findViewById(R.id.strength) as TextView
        val price = itemView.findViewById(R.id.price) as TextView
        val picture = itemView.findViewById(R.id.image) as ImageView
        val addButton = itemView.findViewById(R.id.plus) as ImageView
        val minusButton = itemView.findViewById(R.id.minus) as ImageView
        val addToCart = itemView.findViewById(R.id.addToCart) as Button
    }

    fun setData(medicineList: MutableList<Medicine>){
        this.list = medicineList
        notifyDataSetChanged()
    }
}