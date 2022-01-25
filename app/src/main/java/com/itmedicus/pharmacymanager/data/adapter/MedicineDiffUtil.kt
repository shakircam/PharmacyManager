package com.itmedicus.pharmacymanager.data.adapter

import androidx.recyclerview.widget.DiffUtil
import com.itmedicus.pharmacymanager.model.Medicine

class MedicineDiffUtil(
    private val oldList: List<Medicine>,
    private val newList: List<Medicine>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].medicineName == newList[newItemPosition].medicineName
                && oldList[oldItemPosition].medicineTitle == newList[newItemPosition].medicineTitle
                && oldList[oldItemPosition].generic == newList[newItemPosition].generic
                && oldList[oldItemPosition].strength == newList[newItemPosition].strength
                && oldList[oldItemPosition].price == newList[newItemPosition].price
                && oldList[oldItemPosition].picture == newList[newItemPosition].picture
                && oldList[oldItemPosition].itemNumber == newList[newItemPosition].itemNumber
    }
}