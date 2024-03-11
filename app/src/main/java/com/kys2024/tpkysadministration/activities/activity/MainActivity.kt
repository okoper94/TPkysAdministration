package com.kys2024.tpkysadministration.activities.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.databinding.ActivityBoardBinding
import com.kys2024.tpkysadministration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_bnv_person->startActivity(Intent(this,Signin_MembershipActivity::class.java))
            }
            true
        }//bottomNavigation

        binding.cardViewBoard.setOnClickListener { clcikBoard() }
        binding.cardViewArea.setOnClickListener { clickArea() }
        binding.cardViewBehavior.setOnClickListener { clickBehanior() }



    }
    private fun clcikBoard(){
        startActivity(Intent(this,BoardActivity::class.java))

    }
    private fun clickArea(){

    }

    private fun clickBehanior(){

    }


}