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


//    var email = intent.getStringExtra("email")
//    val password = intent.getStringExtra("password")

//    private val passwordEditable:Editable=Editable.Factory.getInstance().newEditable(password)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var email = intent.getStringExtra("email")
        val emailEditable:Editable=Editable.Factory.getInstance().newEditable(email)




        binding.inputLayoutEmail.editText!!.text=emailEditable
//        binding.inputLayoutPassword.editText!!.text=passwordEditable

        binding.bntUpdate.setOnClickListener { clickUpdate() }
        binding.bntDelete.setOnClickListener { clickDelete() }

    }
    private fun clickUpdate(){
//        AlertDialog.Builder(this).setMessage(email).create().show()

    }
    private fun clickDelete(){

    }

}