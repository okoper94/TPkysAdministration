package com.kys2024.tpkysadministration.activities.activity


import android.Manifest
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.app.Person
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.activities.adapter.RecyclerAdapter
import com.kys2024.tpkysadministration.activities.data.All


import com.kys2024.tpkysadministration.activities.data.Item
import com.kys2024.tpkysadministration.activities.data.Responce
import com.kys2024.tpkysadministration.activities.data.list
import com.kys2024.tpkysadministration.activities.fragments.DiseaseListFragment
import com.kys2024.tpkysadministration.activities.fragments.DiseaseMapFragment
import com.kys2024.tpkysadministration.activities.fragments.DiseaseSearchFragment
import com.kys2024.tpkysadministration.activities.network.RetrofitHelper
import com.kys2024.tpkysadministration.activities.network.RetrofitService
import com.kys2024.tpkysadministration.databinding.ActivityBoardBinding
import com.kys2024.tpkysadministration.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
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

    val people:MutableList<Person> = mutableListOf()

    var searchResponse:All?=null


    var myLocation:Location?=null
    val locationProviderClient:FusedLocationProviderClient by lazy { LocationServices.getFusedLocationProviderClient(this) }


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
        val permissionState:Int = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        if (permissionState == PackageManager.PERMISSION_DENIED){
            permissionResultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }else{
            requestMyLocation()
        }
        binding.toolbar.setNavigationOnClickListener { requestMyLocation() }



    }//onCreate method

    val permissionResultLauncher:ActivityResultLauncher<String> =registerForActivityResult(ActivityResultContracts.RequestPermission()){
        if (it) requestMyLocation()
        else Toast.makeText(this, "내 위치정보를 제공하지 않아 검색기능 사용이 제한됩니다.", Toast.LENGTH_SHORT).show()
    }

    private fun requestMyLocation(){
        //요청객체 생성
        val request: LocationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,3000).build()

        //실시간 위치정보 갱신 요청 -퍼미션 체크코드가 있어야만 함        루퍼는 대신쳐다봐주는 스레드 알바생 고용  --------------------------------------------
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return   //-----------------------------------------------------------------------
        }
        locationProviderClient.requestLocationUpdates(request,locationCallback, Looper.getMainLooper()) //이걸 쓰면 계속 오류가 되는데 add check 하면 우르륵 써짐



    }

    //위치정보 갱신때마다 발동하는 콜백 객체
    private val locationCallback= object : LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            super.onLocationResult(p0)

            myLocation=p0.lastLocation

            //위치 탐색이 종료되었으니 내위치 정보 업데이트를 이제 그만...
            locationProviderClient.removeLocationUpdates(this) //this: locationCallback 객체



        }
    }



    private fun clickArea() { //발생지역


    }

    private fun clcikBoard() { //게시판
        startActivity(Intent(this, BoardActivity::class.java))

    }

    private fun clickBehanior() { //행동요령

    }

    private fun clickData() { //질병자료
        val inputStream=assets.open("Sii.json")
        val inputStreamReader=InputStreamReader(inputStream)
        val reader=BufferedReader(inputStreamReader)

        val builder=StringBuilder()
        while (true){
            val line=reader.readLine()?:break

        }

        val json:JSONArray= JSONArray(builder.toString())
        for (i in 0 until json.length()){
            val jo:JSONObject=json.getJSONObject(i)

            var number=jo.getString("number")
            var ct=jo.getString("ct")

            val person= com.kys2024.tpkysadministration.activities.data.Person(list(number, ct))
           // people.add()

        }






    }





    override fun onResume() {
        super.onResume()

        retrofit()


    }



    private  fun retrofit(){



        val retrofit=RetrofitHelper.getRetrofitInstance("https://apis.data.go.kr")
        val retrofitService=retrofit.create(RetrofitService::class.java)
        var call=retrofitService.getDissItem("CJgLKiwoNZUqrSamb6vD/pCPGWJykavSx1D14h7VW1yzF8tj4RtJH/rOWlVAknPZbBtl6REk8QZZGVGvQP+oCw==","10","1","json","1","11")
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
























}//MainTctivity class

