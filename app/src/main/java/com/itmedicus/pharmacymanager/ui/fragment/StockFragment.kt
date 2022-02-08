package com.itmedicus.pharmacymanager.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.itmedicus.pharmacymanager.data.adapter.StockMedicineAdapter
import com.itmedicus.pharmacymanager.databinding.FragmentStockBinding
import com.itmedicus.pharmacymanager.model.PurchaseMedicine
import com.itmedicus.pharmacymanager.ui.viewmodel.MedicineViewModel


class StockFragment : Fragment() {
    private var _binding: FragmentStockBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { StockMedicineAdapter() }
    private lateinit var myViewModel: MedicineViewModel
    var list = mutableListOf<PurchaseMedicine>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStockBinding.inflate(inflater, container, false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]

        initRecyclerView()
        myViewModel.readMedicineFromPurchase.observe(viewLifecycleOwner) {
            list.addAll(it)
            adapter.setData(it)
            binding.recyclerview.scheduleLayoutAnimation()
        }


    }

    private fun initRecyclerView() {
        val mRecyclerView = binding.recyclerview
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL,false)
    }
}