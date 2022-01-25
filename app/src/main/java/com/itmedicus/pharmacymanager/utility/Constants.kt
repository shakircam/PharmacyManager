package com.itmedicus.pharmacymanager.utility

import com.itmedicus.pharmacymanager.model.Medicine

object Constants {

    fun getMedicineList(): ArrayList<Medicine> {

        val array = ArrayList<String>()
        array.add("https://cdn.iconscout.com/icon/free/png-256/pill-1919732-1620243.png")
        array.add("https://cdn.iconscout.com/icon/free/png-256/pill-1919732-1620243.png")
        array.add("https://cdn.iconscout.com/icon/free/png-256/pill-1919732-1620243.png")
        array.add("https://cdn.iconscout.com/icon/free/png-256/pill-1919732-1620243.png")
        array.add("https://cdn.iconscout.com/icon/free/png-256/pill-1919732-1620243.png")
        array.add("https://cdn.iconscout.com/icon/free/png-256/pill-1919732-1620243.png")
        array.add("https://cdn.iconscout.com/icon/free/png-256/pill-1919732-1620243.png")
        array.add("https://cdn.iconscout.com/icon/free/png-256/pill-1919732-1620243.png")
        array.add("https://cdn.iconscout.com/icon/free/png-256/pill-1919732-1620243.png")

        val list = ArrayList<Medicine>()
        val item1 = Medicine(
            "Capsule", "Napa", "(100mg)", "Paracetamol", 10, (array[0]),1)
        val item2 = Medicine(
            "Capsule", "Tapnil", "(200mg)", "Paracetamol", 10, (array[1]),1)
        val item3 = Medicine(
            "Capsule", "Emcil", "(400mg)", "Paracetamol", 10, (array[2]),1)
        val item4 = Medicine(
            "Capsule", "Omiplasol", "(50mg)", "Paracetamol", 10, (array[3]),1)
        val item5 = Medicine(
            "Capsule", "Ebatin", "(10mg)", "Paracetamol", 10, (array[4]),1)
        val item6 = Medicine(
            "Capsule", "Yamadin", "(20mg)", "Paracetamol", 10, (array[5]),1)
        val item7 = Medicine(
            "Capsule", "Deflacort", "(6mg)", "Paracetamol", 10, (array[6]),1)
        val item8 = Medicine(
            "Capsule", "Trai", "(100mg)", "Paracetamol", 10, (array[7]),1)
        val item9 = Medicine(
            "Capsule", "Moncil", "(100mg)", "Paracetamol", 10, (array[8]),1)

        list.add(item1)
        list.add(item2)
        list.add(item3)
        list.add(item4)
        list.add(item5)
        list.add(item6)
        list.add(item7)
        list.add(item8)
        list.add(item9)

        return list
    }

}