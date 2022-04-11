package com.itmedicus.pharmacymanager.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.itmedicus.pharmacymanager.data.adapter.MedicineAdapter
import com.itmedicus.pharmacymanager.databinding.FragmentMedicineListBinding
import com.itmedicus.pharmacymanager.model.CartMedicine
import com.itmedicus.pharmacymanager.model.Medicine
import com.itmedicus.pharmacymanager.ui.viewmodel.MedicineViewModel
import com.itmedicus.pharmacymanager.utils.Constants
import com.itmedicus.pharmacymanager.utils.ItemClickListener


class MedicineListFragment : Fragment(),ItemClickListener {
    private var _binding : FragmentMedicineListBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { MedicineAdapter(this) }
    private lateinit var myViewModel: MedicineViewModel
    var list = mutableListOf<Medicine>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMedicineListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]
        addDataToDb()
        initRecyclerView()
        myViewModel.readMedicineData.observe(viewLifecycleOwner) {
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

    private fun addDataToDb(){
         val medicine = Constants.getMedicineList()
         myViewModel.insertMedicineData(medicine)
    }

    override fun onItemSend(cartMedicine: CartMedicine) {
        myViewModel.insertMedicineToCart(cartMedicine)
    }
    override fun onItemDelete(cartMedicine: CartMedicine, position: Int) {
        // nothing
    }

}