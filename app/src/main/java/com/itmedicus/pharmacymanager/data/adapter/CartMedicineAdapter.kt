package com.itmedicus.pharmacymanager.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itmedicus.pharmacymanager.R
import com.itmedicus.pharmacymanager.model.CartMedicine
import com.itmedicus.pharmacymanager.utility.ItemClickListener

class CartMedicineAdapter(private val clickListener: ItemClickListener) : RecyclerView.Adapter<CartMedicineAdapter.CartViewHolder> ()  {
    var list = mutableListOf<CartMedicine>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.medicine_cart_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = list[position]

        holder.medicineTitle.text = currentItem.medicineTitle
        holder.medicineName.text = currentItem.medicineName
        holder.generic.text = currentItem.strength
        holder.strength.text = currentItem.generic
        holder.price.text = currentItem.price.toString()
        holder.itemNumber.text = currentItem.itemNumber.toString()

        var currentNumber = currentItem.itemNumber
        val basePrice = currentItem.price / currentNumber
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

        holder.deleteItem.setOnClickListener {

            clickListener.onItemDelete(currentItem,position)

        }

        Glide.with(holder.picture)
            .load(currentItem.picture)
            .into(holder.picture)


    }

    override fun getItemCount(): Int {
        return list.size
    }

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val medicineTitle = itemView.findViewById(R.id.capsule) as TextView
        val medicineName = itemView.findViewById(R.id.brand_name_text) as TextView
        val generic = itemView.findViewById(R.id.generic) as TextView
        val strength = itemView.findViewById(R.id.strength) as TextView
        val price = itemView.findViewById(R.id.price) as TextView
        val picture = itemView.findViewById(R.id.image) as ImageView
        val addButton = itemView.findViewById(R.id.plus) as ImageView
        val minusButton = itemView.findViewById(R.id.minus) as ImageView
        val itemNumber = itemView.findViewById(R.id.amount) as TextView
        val deleteItem = itemView.findViewById(R.id.deleteItem) as ImageView
    }

    fun setData(medicineList: MutableList<CartMedicine>){
        val cartDiffUtil = CartMedicineDiffUtil(list, medicineList)
        val cartDiffResult = DiffUtil.calculateDiff(cartDiffUtil)
        this.list = medicineList
        cartDiffResult.dispatchUpdatesTo(this)
    }

}