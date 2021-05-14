package com.example.advfarmerprojectuas.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.advfarmerprojectuas.R
import com.example.advfarmerprojectuas.databinding.FoodlogItemBinding
import com.example.advfarmerprojectuas.model.Log

class FoodLogListAdapter(val logList:ArrayList<Log>):RecyclerView.Adapter<FoodLogListAdapter.FoodLogListViewHolder>() {
    class FoodLogListViewHolder(var view: FoodlogItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodLogListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<FoodlogItemBinding>(inflater, R.layout.foodlog_item, parent,false)
        return FoodLogListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodLogListViewHolder, position: Int) {
        holder.view.log = logList[position]
    }

    override fun getItemCount(): Int {
        return logList.size
    }

    fun updateLogList(newLogList:List<Log>){
        logList.clear()
        logList.addAll(newLogList)
        notifyDataSetChanged()
    }

}