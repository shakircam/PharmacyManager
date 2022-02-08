package com.itmedicus.pharmacymanager.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.itmedicus.pharmacymanager.R
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.itmedicus.pharmacymanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.navHostFragment)
        //bottom nav
        binding.bottomNavigationView.setupWithNavController(navController)

         appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment,
            R.id.salesFragment,
            R.id.noteFragment,
            R.id.reportFragment)
            ,binding.drawerLayout)

        //menu item click handle
        binding.navigationView.setupWithNavController(navController)
        // connect appbar with nav controller
        setupActionBarWithNavController(navController, appBarConfiguration)

        //hide bottom navigation in specific fragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.medicine,
                R.id.cart,R.id.loginFragment,
                R.id.registrationFragment,
                R.id.stock,R.id.profile,
                R.id.editProfileFragment,
                R.id.purchaseCartFragment ->
                    binding.bottomNavigationView.visibility = View.GONE
                else ->
                    binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }


    //open drawer when drawer icon clicked and back btn pressed
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navHostFragment).navigateUp(appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.item_menu, menu)
        return true
    }


}