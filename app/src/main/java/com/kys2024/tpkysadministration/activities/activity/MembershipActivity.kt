package com.kys2024.tpkysadministration.activities.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.databinding.ActivityMembershipBinding

class MembershipActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMembershipBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnLogin.setOnClickListener { clickLoing() }



    }

    private fun clickLoing(){

        //Firebase Firestore DB에 사용자 정보 저장하기
        var email=binding.inputLayoutEmail.editText!!.text.toString()
        var password=binding.inputLayoutPassword.editText!!.text.toString()
        var passwordConfirm=binding.inputLayoutPasswordCheck.editText!!.text.toString()

        //유효성 검사 - 패스워드와 패스워드 확인이 맞는지 검사.
        if (password!=passwordConfirm){
            AlertDialog.Builder(this).setMessage("잘못되었습니다. \n다시 입력하여 주시기 바랍니다.").create().show()
            binding.inputLayoutPasswordCheck.editText!!.selectAll()
            return
        }

        val userRef: CollectionReference=Firebase.firestore.collection("emailUsers")

        userRef.whereEqualTo("email",email).get().addOnSuccessListener {
            if (it.documents.size>0){
                AlertDialog.Builder(this).setMessage("중복된 이메일이 있습니다.").create().show()
                binding.inputLayoutEmail.editText!!.requestFocus()
                binding.inputLayoutEmail.editText!!.selectAll()
            }else{
                val user:MutableMap<String,String> = mutableMapOf()
                user["email"]=email
                user["password"]=password

                userRef.document().set(user).addOnSuccessListener {
                    AlertDialog.Builder(this)
                        .setMessage("축하합니다.\n회원가입이 완료되었습니다.")
                        .setPositiveButton("확인",{p0,p1 -> finish()})
                        .create().show()
                }
            }
        }
    }
}