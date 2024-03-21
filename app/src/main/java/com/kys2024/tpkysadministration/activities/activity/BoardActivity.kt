package com.kys2024.tpkysadministration.activities.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kys2024.tpkysadministration.activities.data.BoardItem
import com.kys2024.tpkysadministration.databinding.ActivityBoardBinding


class BoardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityBoardBinding.inflate(layoutInflater) }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.bnt.setOnClickListener { clickBtnSave() }
        binding.toolbar.setNavigationOnClickListener { clickBtn() }


    }

    private fun clickBtnSave(){

        var email:String = binding.inputLayoutEmail.editText!!.text.toString()
        var password:String = binding.inputLayoutPassword.editText!!.text.toString()

        val data:MutableMap<String,Any> = mutableMapOf()
        data["email"]=email
        data["password"]=password

        val userRef:CollectionReference=Firebase.firestore.collection("user")
        val user: BoardItem = BoardItem(email, password)
        userRef.document().set(user).addOnSuccessListener {
            Toast.makeText(this, "save success", Toast.LENGTH_SHORT).show() }



    }

    private fun clickBtn(){
        startActivity(Intent(this,LastActivity::class.java))

    }

}











