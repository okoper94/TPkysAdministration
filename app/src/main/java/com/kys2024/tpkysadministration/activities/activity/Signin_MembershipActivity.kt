package com.kys2024.tpkysadministration.activities.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.databinding.ActivitySigninMembershipBinding

class Signin_MembershipActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySigninMembershipBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //툴바 뒤로가기 버튼눌렀을때 뒤로가지는 기능
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.tvLogin.setOnClickListener { startActivity(Intent(this,MembershipActivity::class.java)) }
        binding.layoutEmailLogin.setOnClickListener { startActivity(Intent(this,EmailSignInActivity::class.java)) }
        binding.layoutKakaoLogin.setOnClickListener { clickKakao() }
        binding.layoutGoogleLogin.setOnClickListener { clickGoogle() }
        binding.layoutNaverLogin.setOnClickListener { clickNaver() }

    }
    private fun clickKakao(){
        Toast.makeText(this, "카카오로그인", Toast.LENGTH_SHORT).show()
    }

    private fun clickGoogle(){
        Toast.makeText(this, "구글로그인", Toast.LENGTH_SHORT).show()
    }

    private fun clickNaver(){
        Toast.makeText(this, "네이버로그인", Toast.LENGTH_SHORT).show()
    }

}