package com.kys2024.tpkysadministration.activities.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.databinding.ActivityLastBinding
import java.io.InputStream

class LastActivity : AppCompatActivity() {
     lateinit var binding:ActivityLastBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLastBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}