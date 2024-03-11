package com.kys2024.tpkysadministration.activities.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kys2024.tpkysadministration.activities.data.Place
import com.kys2024.tpkysadministration.databinding.RecyclerItemListFragmentBinding

class RecyclerAdapter (val context: Context,val documents:List<Place>):Adapter<RecyclerAdapter.VH>() {

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
        val place: Place =documents[position]
    }

}