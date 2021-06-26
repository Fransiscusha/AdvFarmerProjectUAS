package com.example.advfarmerprojectuas.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.advfarmerprojectuas.R
import com.example.advfarmerprojectuas.databinding.FragmentWelcomeBinding
import com.example.advfarmerprojectuas.model.User
import com.example.advfarmerprojectuas.viewmodel.UserViewModel

class WelcomeFragment : Fragment(), AgeRadioClickListener, CreateUserClickListener {
    private lateinit var viewModel:UserViewModel
    private lateinit var dataBinding:FragmentWelcomeBinding
    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_welcome, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentWelcomeBinding>(inflater, R.layout.fragment_welcome, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.welcomeUserCheck()
        v = view
        dataBinding.listener = this
        dataBinding.radiolistener = this

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
        })

        viewModel.welcomeLD.observe(viewLifecycleOwner, Observer {
            if (it){
                dataBinding.user = User("", 0, 0, 0,0, "Maintain", 0)
            } else {
                val action = WelcomeFragmentDirections.actionFoodLog()
                Navigation.findNavController(v).navigate(action)
            }
        })
    }


    override fun onAgeRadioClick(v: View, obj: User) {
        obj.pgoal = v.tag.toString()
    }

    override fun onCreateUserClick(v: View, obj: User) {
        with(obj){
            if(nama == "" || umur <= 0 || umur == null || weight <= 0 || weight == null || height <= 0 || height == null){
                Toast.makeText(v.context,"Semua input harus diisi dengan benar !!",Toast.LENGTH_SHORT).show()
            }else{
                viewModel.addUser(obj)
                val action = WelcomeFragmentDirections.actionFoodLog()
                Navigation.findNavController(v).navigate(action)
            }
        }
    }
}