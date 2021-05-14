package com.example.advfarmerprojectuas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.advfarmerprojectuas.R
import com.example.advfarmerprojectuas.databinding.FragmentFoodLogBinding
import com.example.advfarmerprojectuas.viewmodel.LogViewModel

class FoodLogFragment : Fragment() {
    private lateinit var viewModel:LogViewModel
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

        viewModel = ViewModelProvider(this).get(LogViewModel::class.java)
    }
}