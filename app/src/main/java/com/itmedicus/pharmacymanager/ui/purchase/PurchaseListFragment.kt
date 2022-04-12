package com.itmedicus.pharmacymanager.ui.purchase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.itmedicus.pharmacymanager.R
import com.itmedicus.pharmacymanager.data.adapter.PurchaseAdapter
import com.itmedicus.pharmacymanager.databinding.FragmentPurchaseBinding
import com.itmedicus.pharmacymanager.databinding.FragmentPurchaseListBinding
import com.itmedicus.pharmacymanager.model.PurchaseCart
import com.itmedicus.pharmacymanager.ui.viewmodel.MedicineViewModel
import com.itmedicus.pharmacymanager.utils.ClickListener


class PurchaseListFragment : Fragment(), ClickListener, SearchView.OnQueryTextListener {
    private var _binding: FragmentPurchaseListBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { PurchaseAdapter(this) }
    private lateinit var myViewModel: MedicineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPurchaseListBinding.inflate(inflater, container, false)
        binding.searchView.setOnQueryTextListener(this)
        setHasOptionsMenu(true)
        myViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]
        initRecyclerView()
        myViewModel.readMedicineData.observe(viewLifecycleOwner){
            adapter.setData(it)
        }
        return binding.root
    }

    private fun initRecyclerView(){
        val mRecyclerView = binding.recyclerview
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
    }

    override fun onItemPurchaseToCart(purchaseCart: PurchaseCart) {

        myViewModel.insertMedicineToPurchaseCart(purchaseCart)

    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchThroughDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {

        if (query != null) {
            searchThroughDatabase(query)
        }
        return true
    }

    private fun searchThroughDatabase(query: String) {
        val searchQuery = "%$query%"
        myViewModel.searchMedicine(searchQuery).observe(this) { list ->
            list.let {
                adapter.setData(it)
            }
        }
    }


}