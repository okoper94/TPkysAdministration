package com.kys2024.tpkysadministration.activities.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.databinding.ActivityBoardBinding

class BoardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityBoardBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.bnt.setOnClickListener { clickBtnSave() }

    }
    private fun clickBtnSave(){

    }


}