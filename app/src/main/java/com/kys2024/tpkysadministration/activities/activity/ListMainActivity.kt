package com.kys2024.tpkysadministration.activities.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.activities.adapter.BoardAdapter
import com.kys2024.tpkysadministration.activities.adapter.RecyclerAdapter
import com.kys2024.tpkysadministration.activities.data.Item
import com.kys2024.tpkysadministration.activities.data.Responce
import com.kys2024.tpkysadministration.databinding.ActivityListMainBinding

class ListMainActivity : AppCompatActivity() {

    val binding by lazy { ActivityListMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        //binding.recyclerView2.adapter=RecyclerAdapter





    }
}