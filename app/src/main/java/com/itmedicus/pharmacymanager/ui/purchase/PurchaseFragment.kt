package com.itmedicus.pharmacymanager.ui.purchase

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.itmedicus.pharmacymanager.R
import com.itmedicus.pharmacymanager.databinding.FragmentPurchaseBinding
import com.itmedicus.pharmacymanager.ui.viewmodel.MedicineViewModel



class PurchaseFragment : Fragment() {
    private var _binding: FragmentPurchaseBinding? = null
    private val binding get() = _binding!!

    private lateinit var myViewModel: MedicineViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPurchaseBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        myViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragments = ArrayList<Fragment>()
        fragments.add(PurchaseListFragment())
        fragments.add(OrderFragment())


        val title = ArrayList<String>()
        title.add("Medicine List")
        title.add("Stock")


        val pagerAdapter = PagerAdapter(fragments,this)

        binding.viewPager2.apply {
            adapter = pagerAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = title[position]
        }.attach()
    }



    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
         menu.findItem(R.id.cart).isVisible = false
        if (menu.findItem(R.id.stock) != null)
            menu.findItem(R.id.stock).isVisible = true
    }


}