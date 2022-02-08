package com.itmedicus.pharmacymanager.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.itmedicus.pharmacymanager.R
import com.itmedicus.pharmacymanager.data.adapter.MedicineAdapter
import com.itmedicus.pharmacymanager.data.adapter.SalesAdapter
import com.itmedicus.pharmacymanager.databinding.FragmentCartBinding
import com.itmedicus.pharmacymanager.databinding.FragmentSearchBinding
import com.itmedicus.pharmacymanager.model.CartMedicine
import com.itmedicus.pharmacymanager.ui.viewmodel.MedicineViewModel
import com.itmedicus.pharmacymanager.utility.ItemClickListener
import kotlinx.coroutines.launch


class SearchFragment : Fragment(), SearchView.OnQueryTextListener,ItemClickListener {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { SalesAdapter(this) }
    private lateinit var myViewModel: MedicineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.searchView.setOnQueryTextListener(this)
        myViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]
        initRecyclerView()
        return binding.root


    }

    private fun initRecyclerView() {
        val mRecyclerView = binding.recyclerview
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
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
        myViewModel.searchMedicineFromPurchase(searchQuery).observe(this) { list ->
            list.let {
                adapter.setData(it)
            }
        }
    }

    override fun onItemSend(cartMedicine: CartMedicine) {
        myViewModel.insertMedicineToCart(cartMedicine)
    }

    override fun onItemDelete(cartMedicine: CartMedicine, position: Int) {
        // nothing
    }


}