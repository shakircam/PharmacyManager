package com.itmedicus.pharmacymanager.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itmedicus.pharmacymanager.R
import com.itmedicus.pharmacymanager.databinding.FragmentHomeBinding
import com.itmedicus.pharmacymanager.databinding.FragmentSalesBinding


class SalesFragment : Fragment() {
    private var _binding: FragmentSalesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSalesBinding.inflate(inflater, container, false)
        return binding.root
    }


}