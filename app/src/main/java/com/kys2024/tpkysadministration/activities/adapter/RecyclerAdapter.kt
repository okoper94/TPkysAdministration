package com.kys2024.tpkysadministration.activities.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kys2024.tpkysadministration.activities.data.Item
import com.kys2024.tpkysadministration.activities.data.Responce
import com.kys2024.tpkysadministration.activities.fragments.DiseaseListFragment
import com.kys2024.tpkysadministration.databinding.RecyclerItemListFragmentBinding
import retrofit2.Response
import kotlin.math.log

class RecyclerAdapter(val context: Context, var item: List<Item>):Adapter<RecyclerAdapter.VH>() {

    inner class VH(val binding: RecyclerItemListFragmentBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater=LayoutInflater.from(context)
        val binding=RecyclerItemListFragmentBinding.inflate(layoutInflater,parent,false)
        return VH(binding)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val items= item[position]

        holder.binding.tvCnt.text= items.cnt.toString()
        holder.binding.tvDisscd.text= items.dissCd
        holder.binding.tvRisk.text= items.risk.toString()
        holder.binding.tvIowrnkzncd.text= items.lowrnkZnCd
        holder.binding.tvZncd.text= items.znCd
        holder.binding.tvDt.text= items.dt
        holder.binding.tvDissriskxpln.text= items.dissRiskXpln
        Log.d("aa", items.dissCd+"aaa")





    }

}