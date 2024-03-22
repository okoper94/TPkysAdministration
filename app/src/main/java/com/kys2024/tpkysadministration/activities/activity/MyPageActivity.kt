package com.kys2024.tpkysadministration.activities.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.activities.fragments.DiseaseListFragment
import com.kys2024.tpkysadministration.activities.fragments.DiseaseMapFragment
import com.kys2024.tpkysadministration.activities.fragments.DiseaseSearchFragment
import com.kys2024.tpkysadministration.databinding.ActivityMyPageBinding

class MyPageActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMyPageBinding.inflate(layoutInflater) }

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





        binding.bottomNavigation.setOnItemSelectedListener{
            when(it.itemId){
                R.id.menu_bnv_person -> startActivity(Intent(this, Signin_MembershipActivity::class.java))
                R.id.menu_bnv_location -> supportFragmentManager.beginTransaction().replace(R.id.container_fragment, DiseaseMapFragment()).commit()
                R.id.menu_bnv_poll -> supportFragmentManager.beginTransaction().replace(R.id.container_fragment, DiseaseListFragment()).commit()
                R.id.menu_bnv_home -> startActivity(Intent(this, MainActivity::class.java))
                R.id.menu_bnv_scarch -> supportFragmentManager.beginTransaction().replace(R.id.container_fragment, DiseaseSearchFragment()).commit()

            }
            true

        }

        binding.li1.setOnClickListener { click1() }   //이용안내
        binding.li2.setOnClickListener { click2() }   //설정
        binding.li3.setOnClickListener { click3() }   //게시판
        binding.li4.setOnClickListener { click4() }   //좋아요
        binding.li5.setOnClickListener { click5() }   //로그아웃

    }
    private fun clickBack(){
        startActivity(Intent(this,MainActivity::class.java))
    }

    private fun click1(){  //이용안내
        Toast.makeText(this, "이용안내 클릭 예정", Toast.LENGTH_SHORT).show()

    }

    private fun click2(){  //설정
        Toast.makeText(this, "설정 예정", Toast.LENGTH_SHORT).show()

    }

    private fun click3(){  //게시판
        startActivity(Intent(this,LastActivity::class.java))

    }

    private fun click4(){  //좋아요
        startActivity(Intent(this,DetailMainActivity::class.java))

    }

    private fun click5(){  //로그아웃
        Toast.makeText(this, "로그아웃 예정", Toast.LENGTH_SHORT).show()

    }

}