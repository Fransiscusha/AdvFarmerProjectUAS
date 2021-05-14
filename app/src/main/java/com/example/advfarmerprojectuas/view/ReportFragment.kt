package com.example.advfarmerprojectuas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advfarmerprojectuas.R
import com.example.advfarmerprojectuas.databinding.FragmentReportBinding
import com.example.advfarmerprojectuas.viewmodel.ReportViewModel
import kotlinx.android.synthetic.main.fragment_report.*
import java.text.SimpleDateFormat
import java.util.*

class ReportFragment : Fragment() {
    private lateinit var dataBinding:FragmentReportBinding
    private lateinit var viewModel: ReportViewModel
    private var reportListAdapter:ReportListAdapter = ReportListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentReportBinding>(inflater, R.layout.fragment_report,container, false)
        return dataBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ReportViewModel::class.java)
        viewModel.refresh()


        recViewReport.layoutManager = LinearLayoutManager(context)

        recViewReport.adapter = reportListAdapter
        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.reportLD.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            reportListAdapter.updateReportList(it)
        })
    }
}