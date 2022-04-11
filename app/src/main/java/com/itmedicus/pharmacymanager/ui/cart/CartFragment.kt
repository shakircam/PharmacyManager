package com.itmedicus.pharmacymanager.ui.cart

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.itmedicus.pharmacymanager.R
import com.itmedicus.pharmacymanager.data.adapter.CartMedicineAdapter
import com.itmedicus.pharmacymanager.databinding.FragmentCartBinding
import com.itmedicus.pharmacymanager.model.CartMedicine
import com.itmedicus.pharmacymanager.ui.viewmodel.MedicineViewModel
import com.itmedicus.pharmacymanager.utils.ItemClickListener


class CartFragment : Fragment(),ItemClickListener {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { CartMedicineAdapter(this) }
    private lateinit var myViewModel: MedicineViewModel
    var list = mutableListOf<CartMedicine>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]

        initRecyclerView()
        myViewModel.readMedicineFromCart.observe(viewLifecycleOwner) {
            list.addAll(it)
            adapter.setData(it)
        }

        binding.fab.setOnClickListener {
            openCalculateBottomDialog()
        }
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

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.cart).isVisible = false
    }

    fun openCalculateBottomDialog(){
        findNavController().navigate(R.id.calculateItemFragment)
    }
}