package com.kys2024.tpkysadministration.activities.activity

import android.app.Activity
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.databinding.ActivityUpdateDeleteBinding

class UpdateDeleteActivity : AppCompatActivity() {

    private val binding by lazy { ActivityUpdateDeleteBinding.inflate(layoutInflater) }









    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //인텐트 작업은 onCreate 부터 가능하다

        var email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        val emailEditable:Editable=Editable.Factory.getInstance().newEditable(email)
        val passwordEditable:Editable=Editable.Factory.getInstance().newEditable(password)

        binding.inputLayoutEmail.editText!!.text=emailEditable
        binding.inputLayoutPassword.editText!!.text=passwordEditable

        binding.bntUpdate.setOnClickListener { clickUpdate() }
        binding.bntDelete.setOnClickListener { clickDelete() }

    }
    private fun clickUpdate(){
//        AlertDialog.Builder(this).setMessage(email).create().show()

    }
    private fun clickDelete(){

    }

}