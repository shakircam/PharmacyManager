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
import com.itmedicus.pharmacymanager.model.PurchaseCart
import com.itmedicus.pharmacymanager.utils.ClickListener


class PurchaseAdapter(private val clickListener: ClickListener): RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder> () {

    var list = mutableListOf<Medicine>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        return PurchaseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_purchase, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val currentItem = list[position]

        holder.medicineTitle.text = currentItem.medicineTitle
        holder.medicineName.text = currentItem.medicineName
        holder.generic.text = currentItem.strength
        holder.strength.text = currentItem.generic
        holder.purchasePrice.text = currentItem.price.toString()

        Glide.with(holder.picture)
            .load(currentItem.picture)
            .into(holder.picture)

        holder.addToPurchase.setOnClickListener {
            val sPrice  = holder.sellingPrice.text.toString()
            val quantity  = holder.itemNumber.text.toString()

            val purchaseItem = PurchaseCart(
                currentItem.medicineTitle,
                currentItem.medicineName,
                currentItem.generic,
                currentItem.strength,
                currentItem.price,
                Integer.parseInt(sPrice),
                currentItem.picture,
                Integer.parseInt(quantity))
            clickListener.onItemPurchaseToCart(purchaseItem)

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class PurchaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val medicineTitle = itemView.findViewById(R.id.capsule) as TextView
        val medicineName = itemView.findViewById(R.id.brand_name_text) as TextView
        val generic = itemView.findViewById(R.id.generic) as TextView
        val strength = itemView.findViewById(R.id.strength) as TextView
        val purchasePrice = itemView.findViewById(R.id.purchasePrice) as TextView
        val sellingPrice = itemView.findViewById(R.id.sellingPrice) as TextView
        val picture = itemView.findViewById(R.id.image) as ImageView
        val itemNumber = itemView.findViewById(R.id.itemNumber) as TextView
        val addToPurchase = itemView.findViewById(R.id.addToPurchase) as Button

    }

    fun setData(medicineList: MutableList<Medicine>){
        this.list = medicineList
        notifyDataSetChanged()
    }
}