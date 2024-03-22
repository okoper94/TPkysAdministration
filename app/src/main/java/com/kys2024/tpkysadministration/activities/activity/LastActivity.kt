package com.kys2024.tpkysadministration.activities.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.activities.adapter.BoardAdapter
import com.kys2024.tpkysadministration.activities.data.BoardItem
import com.kys2024.tpkysadministration.databinding.ActivityLastBinding

class LastActivity : AppCompatActivity() {
    lateinit var binding: ActivityLastBinding

    val boardItem: MutableList<BoardItem> = mutableListOf()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { clickBack() }


        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {

                R.id.menu_login2-> Toast.makeText(this, "검색 예정", Toast.LENGTH_SHORT).show()
            }
            true
        }



        binding.floating.setOnClickListener { clickPost() }

        binding.toolbar.setNavigationOnClickListener { clicktt() }

        binding.recyclerView2.adapter = BoardAdapter(this, boardItem)

        val userRef: CollectionReference = Firebase.firestore.collection("user")
        userRef.get().addOnSuccessListener {


            for (snapshot: DocumentSnapshot in it) {
                val data: Map<String, Any> = snapshot.data!!
                var email: String = data["email"].toString()
                var password: String = data["password"].toString()

                boardItem.add(BoardItem(email, password))

            }
            boardItem.reverse()
            binding.recyclerView2.adapter!!.notifyDataSetChanged()


        }


    }
    private fun clickBack(){
        startActivity(Intent(this,MainActivity::class.java))

    }




    private fun clickPost(){
        startActivity(Intent(this,BoardActivity::class.java))


    }




    private fun clicktt(){
        startActivity(Intent(this,MainActivity::class.java))
    }
}




