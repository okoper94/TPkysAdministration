package com.kys2024.tpkysadministration.activities.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.databinding.ActivityEmailSignInBinding

class EmailSignInActivity : AppCompatActivity() {

    private val binding by lazy { ActivityEmailSignInBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_sign_in)

        binding.toolbar.setNavigationOnClickListener { finish() }
    }
}