package com.kys2024.tpkysadministration.activities.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.databinding.ActivityDetailMainBinding

class DetailMainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { clickBack() }


        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {

                R.id.menu_login2-> Toast.makeText(this, "검색 예정", Toast.LENGTH_SHORT).show()
            }
            true
        }






    }
    private fun clickBack(){
        startActivity(Intent(this,MainActivity::class.java))

    }

}