package com.example.advfarmerprojectuas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.advfarmerprojectuas.R
import com.example.advfarmerprojectuas.databinding.FragmentFoodLogBinding
import com.example.advfarmerprojectuas.viewmodel.LogViewModel
import com.example.advfarmerprojectuas.viewmodel.UserViewModel

class FoodLogFragment : Fragment(), LogAMealListener {
    private lateinit var userViewModel:UserViewModel
    private lateinit var logViewModel:LogViewModel
    private lateinit var dataBinding:FragmentFoodLogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentFoodLogBinding>(inflater, R.layout.fragment_food_log,container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.fablistener = this

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.fetchUser()

        observeViewModel()
    }

    fun observeViewModel(){
        userViewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
        })
    }

    override fun onLogAMeal(v: View) {
        val action = FoodLogFragmentDirections.actionMealFragment()
        Navigation.findNavController(v).navigate(action)
    }
}