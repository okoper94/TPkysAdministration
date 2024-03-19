package com.kys2024.tpkysadministration.activities.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.activities.G
import com.kys2024.tpkysadministration.activities.data.UserAccount
import com.kys2024.tpkysadministration.activities.network.RetrofitHelper
import com.kys2024.tpkysadministration.databinding.ActivitySigninMembershipBinding
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Signin_MembershipActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySigninMembershipBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //툴바 뒤로가기 버튼눌렀을때 뒤로가지는 기능
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.tvLogin.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MembershipActivity::class.java
                )
            )
        }
        binding.layoutEmailLogin.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    EmailSignInActivity::class.java
                )
            )
        }
        binding.layoutKakaoLogin.setOnClickListener { clickKakao() }
        binding.layoutGoogleLogin.setOnClickListener { clickGoogle() }
       // binding.layoutNaverLogin.setOnClickListener { clickNaver() }

    }

    private fun clickKakao() {
        //두개의 로그인 요청 콜백함수
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Toast.makeText(this, "카카오로그인 실패", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "카카오로그인 성공", Toast.LENGTH_SHORT).show()

                //사용자 정보 요청
                UserApiClient.instance.me { user, error ->
                    if (user != null) {
                        val id: String = user.id.toString()
                        val nickname: String = user.kakaoAccount?.profile?.nickname ?: ""

                        Toast.makeText(this, "$id\n$nickname", Toast.LENGTH_SHORT).show()
                        G.userAccount = UserAccount(id, nickname)


                        //로그인 되었으니..
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
            }

        }

        //카카오톡이 사용가능하면 이를 이용하여 로그인하고 없으면 그냥 카카오계정으로 로그인하기
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }

    private fun clickGoogle() {
        //로그인 옵션객체 생성- Builder-이메일 요청..
        val signInOptions: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()

        //구글 로그인을 하는 화면 액티비티를 실행하는Intent 객체로 로그인 구현
        val intent: Intent = GoogleSignIn.getClient(this, signInOptions).signInIntent
        resultLauncher.launch(intent)


    }

    //구글 로그인화면 결과를 받아주는 대행사 등록
    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            //로그인 결과를 가져온 인텐트 소환
            val intent: Intent? = it.data
            //인텐트로 부터 구글 계정 정보를 가져오는 작업자 객체를 소환
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(intent)

            //작업자로부터 계정 받기
            val account: GoogleSignInAccount = task.result
            val id: String = account.id.toString()
            val email: String = account.email ?: ""

            Toast.makeText(this, "$id\n$email", Toast.LENGTH_SHORT).show()
            G.userAccount = UserAccount(id, email)

            //main 화면으로 이동
            startActivity(Intent(this, MainActivity::class.java))
            finish()


        }
}

//    private fun clickNaver(){
//        //네아로 SDK 초기화
//        NaverIdLoginSDK.initialize(this,"vIUJZ7U4yLjy7WvMIYej","brbGSYJ1sl","질병예측")
//
//        //로그인 요청
//        NaverIdLoginSDK.authenticate(this,object : OAuthLoginCallback {
//            override fun onError(errorCode: Int, message: String) {
//                Toast.makeText(this@Signin_MembershipActivity, "$message", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onFailure(httpStatus: Int, message: String) {
//                Toast.makeText(this@Signin_MembershipActivity, "$message", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onSuccess() {
//                Toast.makeText(this@Signin_MembershipActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
//
//                //사용자 정보를 받아오기 --REST API 로 받아야함
//                //로그인에 성공하면 REST API로 요청할 수 있는 토큰(token)을 발급받음.
//                val accessToken:String? = NaverIdLoginSDK.getAccessToken()
//
//                //Retrofit 작업을 통해 사용자 정보 가져오기
//                val retrofit=RetrofitHelper.getRetrofitInstance("https://openapi.naver.com")
//                val retrofitService=retrofit.create(RetrofitHelper::class.java)
//                val call=retrofitService.getNidUserInfo("Bearer ${accessToken}")
//
//                call.enqueue(object : Callback<String> {
//                    override fun onResponse(call: Call<String>, response: Response<String>) {
//                        val s=response.body()
//                        AlertDialog.Builder(this@Signin_MembershipActivity).setMessage(s).create().show()
//
//                        startActivity(Intent(this@Signin_MembershipActivity,MainActivity::class.java))
//                        finish()
//                    }
//
//                    override fun onFailure(call: Call<String>, t: Throwable) {
//                        Toast.makeText(this@Signin_MembershipActivity, "${t.message}", Toast.LENGTH_SHORT).show()
//                    }
//
//                })
//
//
//            }
//
//        })
//
//    }

//}