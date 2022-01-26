package com.itmedicus.pharmacymanager.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.itmedicus.pharmacymanager.R
import com.itmedicus.pharmacymanager.data.adapter.MedicineAdapter
import com.itmedicus.pharmacymanager.data.adapter.PurchaseAdapter
import com.itmedicus.pharmacymanager.databinding.FragmentHomeBinding
import com.itmedicus.pharmacymanager.databinding.FragmentSalesBinding
import com.itmedicus.pharmacymanager.model.PurchaseMedicine
import com.itmedicus.pharmacymanager.ui.viewmodel.MedicineViewModel
import com.itmedicus.pharmacymanager.utility.ClickListener


class SalesFragment : Fragment(),ClickListener, SearchView.OnQueryTextListener {
    private var _binding: FragmentSalesBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { PurchaseAdapter(this) }
    private lateinit var myViewModel: MedicineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSalesBinding.inflate(inflater, container, false)
        binding.searchView.setOnQueryTextListener(this)
        myViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView(){
        val mRecyclerView = binding.recyclerview
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
    }

    override fun onItemPurchase(purchaseMedicine: PurchaseMedicine) {
        myViewModel.insertMedicineToPurchase(purchaseMedicine)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchThroughDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            binding.recyclerview.isVisible = true
            searchThroughDatabase(query)
        }

        if (query.isNullOrEmpty()){
            binding.recyclerview.isVisible = false
        }
        return true
    }

    private fun searchThroughDatabase(query: String) {
        val searchQuery = "%$query%"
        myViewModel.searchMedicine(searchQuery).observe(this,{list ->
            list.let {
                adapter.setData(it)
            }
        })
    }


}