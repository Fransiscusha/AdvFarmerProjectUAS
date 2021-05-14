package com.example.advfarmerprojectuas.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.advfarmerprojectuas.R
import com.example.advfarmerprojectuas.databinding.ReportItemBinding
import com.example.advfarmerprojectuas.model.Report

class ReportListAdapter(val reportList:ArrayList<Report>):RecyclerView.Adapter<ReportListAdapter.ReportListViewHolder>(){
    class ReportListViewHolder(var view: ReportItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ReportItemBinding>(inflater,
            R.layout.report_item,parent,false)
        return ReportListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportListViewHolder, position: Int) {
        holder.view.report= reportList[position]
    }

    fun updateReportList(newReportList:List<Report>){
        reportList.clear()
        reportList.addAll(newReportList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return reportList.size
    }

}
