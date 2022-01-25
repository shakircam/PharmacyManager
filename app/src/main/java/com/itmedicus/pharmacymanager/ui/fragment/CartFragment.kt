package com.itmedicus.pharmacymanager.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.itmedicus.pharmacymanager.R
import com.itmedicus.pharmacymanager.data.adapter.CartMedicineAdapter
import com.itmedicus.pharmacymanager.data.adapter.MedicineAdapter
import com.itmedicus.pharmacymanager.databinding.FragmentCartBinding
import com.itmedicus.pharmacymanager.databinding.FragmentHomeBinding
import com.itmedicus.pharmacymanager.model.CartMedicine
import com.itmedicus.pharmacymanager.model.CartPrice
import com.itmedicus.pharmacymanager.model.Medicine
import com.itmedicus.pharmacymanager.ui.viewmodel.MedicineViewModel
import com.itmedicus.pharmacymanager.utility.ItemClickListener


class CartFragment : Fragment(),ItemClickListener {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { CartMedicineAdapter(this) }
    private lateinit var myViewModel: MedicineViewModel
    var list = mutableListOf<CartMedicine>()
    var totalAmount = 0
    val cartItems = mutableListOf<CartPrice>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]

        initRecyclerView()
        myViewModel.readMedicineFromCart.observe(this,{
            list.addAll(it)
            adapter.setData(it)
        })

        myViewModel.getCartPrice.observe(this,{
            cartItems.addAll(it)
            for (i in cartItems.indices){
                totalAmount += cartItems[i].price
            }

            binding.price.text ="$totalAmount"
        })
    }

    private fun initRecyclerView() {
        val mRecyclerView = binding.recyclerview
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL,false)
    }

    override fun onItemSend(cartMedicine: CartMedicine) {
        // nothing
    }

    override fun onItemDelete(cartMedicine: CartMedicine, position: Int) {
        myViewModel.deleteMedicineFromCart(cartMedicine)
        adapter.notifyItemRemoved(position)
    }


}