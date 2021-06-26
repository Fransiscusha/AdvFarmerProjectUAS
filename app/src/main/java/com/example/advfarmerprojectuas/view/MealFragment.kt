package com.example.advfarmerprojectuas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.advfarmerprojectuas.R
import com.example.advfarmerprojectuas.databinding.FragmentMealBinding
import com.example.advfarmerprojectuas.databinding.FragmentWelcomeBinding
import com.example.advfarmerprojectuas.model.Log
import com.example.advfarmerprojectuas.viewmodel.LogViewModel
import com.example.advfarmerprojectuas.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.util.*

class MealFragment : Fragment(), CreateLogCliclListener {
    private lateinit var viewModel:UserViewModel
    private lateinit var logViewModel:LogViewModel
    private lateinit var dataBinding:FragmentMealBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_meal, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentMealBinding>(inflater, R.layout.fragment_meal, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.fetchUser()

        logViewModel = ViewModelProvider(this).get(LogViewModel::class.java)
        logViewModel.declareLog()
        logViewModel.getNeedCalories()

        dataBinding.listener = this

        observeViewModel()
    }

    fun observeViewModel() {
        logViewModel.needCaloriesLD.observe(viewLifecycleOwner, Observer {
            dataBinding.needCalories = it
        })

        logViewModel.slogLd.observe(viewLifecycleOwner, Observer {
            dataBinding.log = it
        })
    }

    override fun onCreateLogClick(v: View, obj: Log) {
        with(obj) {
            if (nama == "" || kalori.toString() == "" || kalori <= 0) {
                Toast.makeText(v.context, "Semua input harus diisi dengan benar !!", Toast.LENGTH_SHORT).show()
            } else {
                logViewModel.addLog(obj.nama, obj.kalori)
                Toast.makeText(v.context, "Log added", Toast.LENGTH_SHORT).show()
            }
        }
    }
}