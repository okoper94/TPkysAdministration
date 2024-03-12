package com.kys2024.tpkysadministration.activities.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.activities.fragments.DiseaseListFragment
import com.kys2024.tpkysadministration.activities.fragments.DiseaseMapFragment
import com.kys2024.tpkysadministration.activities.fragments.DiseaseSearchFragment
import com.kys2024.tpkysadministration.databinding.ActivityBoardBinding
import com.kys2024.tpkysadministration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.container_fragment,DiseaseListFragment()).commit()

        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_login-> startActivity(Intent(this,Signin_MembershipActivity::class.java))
                //R.id.menu_sign_out->  로그아웃 아직 않함
            }
            true
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){   //네비게이션뷰 클릭시 화면전환
                R.id.menu_bnv_person->startActivity(Intent(this,Signin_MembershipActivity::class.java))
                R.id.menu_bnv_location->supportFragmentManager.beginTransaction().replace(R.id.container_fragment,DiseaseMapFragment()).commit()
                R.id.menu_bnv_poll->supportFragmentManager.beginTransaction().replace(R.id.container_fragment,DiseaseListFragment()).commit()
                R.id.menu_bnv_home->startActivity(Intent(this,MainActivity::class.java))
                R.id.menu_bnv_scarch->supportFragmentManager.beginTransaction().replace(R.id.container_fragment,DiseaseSearchFragment()).commit()


            }
            true
        }//bottomNavigation

        binding.cardViewBoard.setOnClickListener { clcikBoard() }
        binding.cardViewArea.setOnClickListener { clickArea() }
        binding.cardViewBehavior.setOnClickListener { clickBehanior() }
        binding.cardViewData.setOnClickListener { clickData() }



    }

    private fun clickArea(){ //발생지역

    }

    private fun clcikBoard(){ //게시판
        startActivity(Intent(this,BoardActivity::class.java))

    }

    private fun clickBehanior(){ //행동요령

    }
    private fun clickData(){ //질병자료

    }


}