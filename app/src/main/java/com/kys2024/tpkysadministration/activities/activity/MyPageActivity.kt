package com.kys2024.tpkysadministration.activities.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.databinding.ActivityMyPageBinding

class MyPageActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMyPageBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener{
            when(it.itemId){
                R.id.menu_bnv_home-> startActivity(Intent(this,MainActivity::class.java))


            }
            true

        }

        binding.li1.setOnClickListener { click1() }   //이용안내
        binding.li2.setOnClickListener { click2() }   //설정
        binding.li3.setOnClickListener { click3() }   //게시판
        binding.li4.setOnClickListener { click4() }   //좋아요
        binding.li5.setOnClickListener { click5() }   //로그아웃

    }
    private fun click1(){  //이용안내

    }

    private fun click2(){  //설정

    }

    private fun click3(){  //게시판
        startActivity(Intent(this,BoardActivity::class.java))

    }

    private fun click4(){  //좋아요

    }

    private fun click5(){  //로그아웃

    }

}