package com.kys2024.tpkysadministration.activities.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.activities.G
import com.kys2024.tpkysadministration.activities.data.UserAccount
import com.kys2024.tpkysadministration.databinding.ActivityEmailSignInBinding

class EmailSignInActivity : AppCompatActivity() {

    private val binding by lazy { ActivityEmailSignInBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.btnLogin.setOnClickListener { clickLogin() }
    }
    private fun clickLogin(){
        var email=binding.inputLayoutEmail.editText!!.text.toString()
        var password=binding.inputLayoutPassword.editText!!.text.toString()

        val userRef: CollectionReference=Firebase.firestore.collection("emailUsers")
        userRef
            .whereEqualTo("email",email)
            .whereEqualTo("password",password)
            .get().addOnSuccessListener {
                if (it.documents.size>0){
                    val id:String = it.documents[0].id
                    G.userAccount= UserAccount(id, email)

                    val intent=Intent(this,MainActivity::class.java)

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) //화면에 거쳐서 도달하다보면 그전의 화면을 꺼주는 기능
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                    startActivity(intent)

                }else{
                    AlertDialog.Builder(this).setMessage("이메일과 비밀번호를 다시 확인해주세요.").create().show()
                    binding.inputLayoutEmail.editText!!.requestFocus()
                    binding.inputLayoutEmail.editText!!.selectAll()
                }
            }


    }


}