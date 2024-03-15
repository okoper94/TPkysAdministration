package com.kys2024.tpkysadministration.activities.activity


import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.activities.adapter.RecyclerAdapter
import com.kys2024.tpkysadministration.activities.data.All


import com.kys2024.tpkysadministration.activities.data.Item
import com.kys2024.tpkysadministration.activities.data.Responce
import com.kys2024.tpkysadministration.activities.fragments.DiseaseListFragment
import com.kys2024.tpkysadministration.activities.fragments.DiseaseMapFragment
import com.kys2024.tpkysadministration.activities.fragments.DiseaseSearchFragment
import com.kys2024.tpkysadministration.activities.network.RetrofitHelper
import com.kys2024.tpkysadministration.activities.network.RetrofitService
import com.kys2024.tpkysadministration.databinding.ActivityBoardBinding
import com.kys2024.tpkysadministration.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    var searchResponse:All?=null
    var q:String="1"
    var w:String="1"
    var e:String="1"
    var r:String="1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.container_fragment, DiseaseListFragment()).commit()

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_login -> startActivity(Intent(this, Signin_MembershipActivity::class.java))
                R.id.menu_bnv_poll-> supportFragmentManager.beginTransaction().replace(R.id.container_fragment,DiseaseListFragment()).commit()
                R.id.menu_my_page -> startActivity(Intent(this, MyPageActivity::class.java))
                //R.id.menu_sign_out->  로그아웃 아직 않함
            }
            true
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {   //네비게이션뷰 클릭시 화면전환
                R.id.menu_bnv_person -> startActivity(Intent(this, Signin_MembershipActivity::class.java))
                R.id.menu_bnv_location -> supportFragmentManager.beginTransaction().replace(R.id.container_fragment, DiseaseMapFragment()).commit()
                R.id.menu_bnv_poll -> supportFragmentManager.beginTransaction().replace(R.id.container_fragment, DiseaseListFragment()).commit()
                R.id.menu_bnv_home -> startActivity(Intent(this, MainActivity::class.java))
                R.id.menu_bnv_scarch -> supportFragmentManager.beginTransaction().replace(R.id.container_fragment, DiseaseSearchFragment()).commit()




            }
               true
        }//bottomNavigation

        binding.cardViewBoard.setOnClickListener { clcikBoard() }
        binding.cardViewArea.setOnClickListener { clickArea() }
        binding.cardViewBehavior.setOnClickListener { clickBehanior() }
        binding.cardViewData.setOnClickListener { clickData() }

//        binding.se.setOnClickListener { startActivity(Intent(Intent(this,LastActivity::class.java))) }
//        binding.busan.setOnClickListener { startActivity(Intent(Intent(this,LastActivity::class.java))) }
//        binding.degu.setOnClickListener { startActivity(Intent(Intent(this,LastActivity::class.java))) }
//        binding.inchun.setOnClickListener { startActivity(Intent(Intent(this,LastActivity::class.java))) }
//        binding.gwangJu.setOnClickListener { startActivity(Intent(Intent(this,LastActivity::class.java))) }
//        binding.deJun.setOnClickListener { startActivity(Intent(Intent(this,LastActivity::class.java))) }
//        binding.wulsan.setOnClickListener { startActivity(Intent(Intent(this,LastActivity::class.java))) }
//        binding.gwuhgGi.setOnClickListener { startActivity(Intent(Intent(this,LastActivity::class.java))) }
//        binding.gwanhWon.setOnClickListener { startActivity(Intent(Intent(this,LastActivity::class.java))) }
//        binding.choongBook.setOnClickListener { startActivity(Intent(Intent(this,LastActivity::class.java))) }
//        binding.choongNam.setOnClickListener { startActivity(Intent(Intent(this,LastActivity::class.java))) }
//        binding.junBook.setOnClickListener { startActivity(Intent(Intent(this,LastActivity::class.java))) }
//        binding.junNam.setOnClickListener { startActivity(Intent(Intent(this,LastActivity::class.java))) }
//        binding.gungNam.setOnClickListener { startActivity(Intent(Intent(this,LastActivity::class.java))) }
//        binding.gungBook.setOnClickListener { startActivity(Intent(Intent(this,LastActivity::class.java))) }
//        binding.jeJu.setOnClickListener { startActivity(Intent(Intent(this,LastActivity::class.java))) }



    }


    private fun clickArea() { //발생지역


    }

    private fun clcikBoard() { //게시판
        startActivity(Intent(this, BoardActivity::class.java))

    }

    private fun clickBehanior() { //행동요령

    }

    private fun clickData() { //질병자료

    }

    private fun clickChoice(view:android.view.View){


    }

    override fun onResume() {
        super.onResume()

        retrofit()


    }



    private  fun retrofit(){

        val retrofit=RetrofitHelper.getRetrofitInstance("https://apis.data.go.kr")
        val retrofitService=retrofit.create(RetrofitService::class.java)
        var call=retrofitService.getDissItem("CJgLKiwoNZUqrSamb6vD/pCPGWJykavSx1D14h7VW1yzF8tj4RtJH/rOWlVAknPZbBtl6REk8QZZGVGvQP+oCw==","10",q,"json","1","11")
        call.enqueue(object :Callback<All>{
            override fun onResponse(call: Call<All>, response: Response<All>) {
//                val ss =response.body()
//                AlertDialog.Builder(this@MainActivity).setMessage("$ss").create().show()
                searchResponse = response.body()

                binding.bottomNavigation.selectedItemId=R.id.menu_bnv_poll






            }

            override fun onFailure(call: Call<All>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
