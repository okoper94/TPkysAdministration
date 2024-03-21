package com.kys2024.tpkysadministration.activities.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kys2024.tpkysadministration.activities.activity.UpdateDeleteActivity
import com.kys2024.tpkysadministration.activities.data.BoardItem
import com.kys2024.tpkysadministration.databinding.RecyclerItListBoradBinding

class BoardAdapter(var context: Context, var boarditem: MutableList<BoardItem>):
    Adapter<BoardAdapter.VH>() {


    inner class VH(val binding: RecyclerItListBoradBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater = LayoutInflater.from(context)
        val binding = RecyclerItListBoradBinding.inflate(layoutInflater, parent, false)
        return VH(binding)
    }

    override fun getItemCount(): Int {
        return boarditem.size
    }




    override fun onBindViewHolder(holder: VH, position: Int) {
        val items = boarditem[position]

        holder.binding.tvEmail.text = ("제목 : ${items.email}")
        holder.binding.tvPassword.text = ("내용 : ${items.password}")

        holder.binding.root.setOnClickListener {

            AlertDialog.Builder(context).setMessage(items.email).create().show()



            val intent = Intent(context, UpdateDeleteActivity::class.java)
            intent.putExtra("email",items.email)
            intent.putExtra("password",items.password)
            context.startActivity(intent)











        }
    }

}