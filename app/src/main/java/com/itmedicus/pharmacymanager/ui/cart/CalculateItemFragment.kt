package com.itmedicus.pharmacymanager.ui.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.itmedicus.pharmacymanager.R
import com.itmedicus.pharmacymanager.databinding.FragmentCalculateItemBinding
import com.itmedicus.pharmacymanager.ui.viewmodel.MedicineViewModel


class CalculateItemFragment : BottomSheetDialogFragment() {
    private var _binding : FragmentCalculateItemBinding? = null
    private val binding get() = _binding!!
    private lateinit var myViewModel: MedicineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCalculateItemBinding.inflate(inflater, container, false)
        myViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.getCartPrice.observe(viewLifecycleOwner) {
            Log.d("this",it.toString())
            val totalPrice = it.sumOf { it.price }
            binding.price.text = "$totalPrice"
            binding.totalPrice.text = "$totalPrice"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}