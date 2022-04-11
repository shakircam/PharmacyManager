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
import com.itmedicus.pharmacymanager.model.CartMedicine
import com.itmedicus.pharmacymanager.model.PurchaseMedicine
import com.itmedicus.pharmacymanager.utils.ItemClickListener

class SalesAdapter(private val clickListener: ItemClickListener) : RecyclerView.Adapter<SalesAdapter.SalesViewHolder> () {
    var list = mutableListOf<PurchaseMedicine>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesViewHolder {
        return SalesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SalesViewHolder, position: Int) {

        val currentItem = list[position]
        holder.medicineTitle.text = currentItem.medicineTitle
        holder.medicineName.text = currentItem.medicineName
        holder.generic.text = currentItem.strength
        holder.strength.text = currentItem.generic
        holder.price.text = currentItem.sellingPrice.toString()

        Glide.with(holder.picture)
            .load(currentItem.picture)
            .into(holder.picture)

        var currentNumber = 1
        val basePrice = currentItem.sellingPrice / currentNumber
        var increasePrice = currentNumber* basePrice

        holder.addButton.setOnClickListener {

            if (currentNumber < 9) {
                currentNumber += 1
            }
            increasePrice = currentNumber* basePrice
            holder.price.text = "$increasePrice$"
            holder.itemNumber.text = currentNumber.toString()

        }

        holder.minusButton.setOnClickListener {
            if (currentNumber >1){
                currentNumber -=1
            }
            increasePrice = currentNumber* basePrice
            holder.price.text = "$increasePrice$"
            holder.itemNumber.text = currentNumber.toString()

        }

        holder.addToCart.setOnClickListener {

            val cartItem = CartMedicine(
                currentItem.medicineTitle,
                currentItem.medicineName,
                currentItem.generic,
                currentItem.strength,
                increasePrice,
                currentItem.picture,
                currentNumber)
            clickListener.onItemSend(cartItem)
        }
    }

    class SalesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val medicineTitle = itemView.findViewById(R.id.capsule) as TextView
        val medicineName = itemView.findViewById(R.id.brand_name_text) as TextView
        val generic = itemView.findViewById(R.id.generic) as TextView
        val strength = itemView.findViewById(R.id.strength) as TextView
        val price = itemView.findViewById(R.id.price) as TextView
        val picture = itemView.findViewById(R.id.image) as ImageView
        val addButton = itemView.findViewById(R.id.plus) as ImageView
        val minusButton = itemView.findViewById(R.id.minus) as ImageView
        val addToCart = itemView.findViewById(R.id.addToCart) as Button
        val itemNumber = itemView.findViewById(R.id.amount) as TextView
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(medicineList: MutableList<PurchaseMedicine>){
        this.list = medicineList
        notifyDataSetChanged()
    }

}