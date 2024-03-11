package com.kys2024.tpkysadministration.activities.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.databinding.ActivityMembershipBinding

class MembershipActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMembershipBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

    }
}