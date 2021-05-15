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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advfarmerprojectuas.R
import com.example.advfarmerprojectuas.databinding.FoodlogItemBinding
import com.example.advfarmerprojectuas.databinding.FragmentFoodLogBinding
import com.example.advfarmerprojectuas.viewmodel.LogViewModel
import com.example.advfarmerprojectuas.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_food_log.*
import kotlin.math.log

class FoodLogFragment : Fragment(), LogAMealListener {
    private lateinit var userViewModel:UserViewModel
    private lateinit var logViewModel:LogViewModel
    private lateinit var dataBinding:FragmentFoodLogBinding
    private var foodLogListAdapter: FoodLogListAdapter = FoodLogListAdapter(arrayListOf())

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

        logViewModel = ViewModelProvider(this).get(LogViewModel::class.java)
        logViewModel.fetchLog()
        logViewModel.getCurrentCalories()
        logViewModel.getStatus()

        recViewLog.layoutManager = LinearLayoutManager(context)
        recViewLog.adapter = foodLogListAdapter

        observeViewModel()
    }

    fun observeViewModel(){
        userViewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
        })
        logViewModel.logLD.observe(viewLifecycleOwner, Observer {
            foodLogListAdapter.updateLogList(it)
            with(foodLogTextNoData){
                visibility = if(it.isEmpty()) View.VISIBLE else View.GONE
            }
        })
        logViewModel.currentCaloriesLD.observe(viewLifecycleOwner, Observer {
            dataBinding.currentcalories = it
        })
        logViewModel.statusLD.observe(viewLifecycleOwner, Observer {
            dataBinding.status = it
        })
    }

    override fun onLogAMeal(v: View) {
        val action = FoodLogFragmentDirections.actionMealFragment()
        Navigation.findNavController(v).navigate(action)
    }
}