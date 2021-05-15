package com.example.advfarmerprojectuas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.advfarmerprojectuas.R
import com.example.advfarmerprojectuas.databinding.ActivityMainBinding
import com.example.advfarmerprojectuas.databinding.FragmentWelcomeBinding
import com.example.advfarmerprojectuas.model.User
import com.example.advfarmerprojectuas.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    private lateinit var navController: NavController
    private lateinit var dataBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        navController = Navigation.findNavController(this,R.id.navHost)


        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if(destination.id == R.id.welcomeFragment){
                dataBinding.visible = View.GONE
            }else{
                dataBinding.visible = View.VISIBLE
            }
        }
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.profile))
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        if(navController.currentDestination?.id == R.id.mealFragment){
            return NavigationUI.navigateUp(navController, null)
        }
        else{
            return onNavigateUp()
        }
    }
}