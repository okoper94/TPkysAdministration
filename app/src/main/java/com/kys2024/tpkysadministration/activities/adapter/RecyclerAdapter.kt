package com.kys2024.tpkysadministration.activities.adapter

import android.app.LauncherActivity.ListItem
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.gson.Gson
import com.kys2024.tpkysadministration.activities.data.Item
import com.kys2024.tpkysadministration.activities.data.Place
import com.kys2024.tpkysadministration.databinding.RecyclerItemListFragmentBinding

class RecyclerAdapter (val context: Context,val documents:List<Item>):Adapter<RecyclerAdapter.VH>() {

    inner class VH(val binding: RecyclerItemListFragmentBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater=LayoutInflater.from(context)
        val binding=RecyclerItemListFragmentBinding.inflate(layoutInflater,parent,false)
        return VH(binding)
    }

    override fun getItemCount(): Int {
        return documents.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item: Item =documents[position]
        holder.binding.tvCnt.text= item.cnt.toString()
        holder.binding.tvDisscd.text= item.dissCd
        holder.binding.tvRisk.text= item.risk.toString()
        holder.binding.tvIowrnkzncd.text= item.lowrnkZnCd
        holder.binding.tvZncd.text= item.znCd
        holder.binding.tvIowrnkzncd.text= item.znCd
        holder.binding.tvDt.text= item.znCd



    }

}