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
import com.example.advfarmerprojectuas.databinding.FragmentProfileBinding
import com.example.advfarmerprojectuas.model.User
import com.example.advfarmerprojectuas.viewmodel.UserViewModel

class ProfileFragment : Fragment(), UserSaveProfileListener {
    private lateinit var viewModel: UserViewModel
    private lateinit var dataBinding:FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_profile, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater, R.layout.fragment_profile, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.fetchUser()

        dataBinding.listener = this

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
        })
    }

    override fun onUserSaveProfile(v: View, obj: User) {
        with(obj){
            if(nama == "" || umur <= 0 || umur == null || weight <= 0 || weight == null || height <= 0 || height == null ){
                Toast.makeText(v.context,"Semua input harus diisi dengan benar !!",Toast.LENGTH_SHORT).show()
            }else{
                viewModel.updateUserProfile(obj.nama, obj.umur, obj.gender, obj.weight, obj.height, obj.pgoal, obj.uuid)
                Toast.makeText(v.context, "profile updated", Toast.LENGTH_SHORT).show()
            }
        }
    }
}