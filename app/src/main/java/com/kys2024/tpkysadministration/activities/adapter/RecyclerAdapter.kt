package com.kys2024.tpkysadministration.activities.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kys2024.tpkysadministration.activities.data.Item
import com.kys2024.tpkysadministration.activities.data.Responce
import com.kys2024.tpkysadministration.activities.fragments.DiseaseListFragment
import com.kys2024.tpkysadministration.databinding.RecyclerItemListFragmentBinding
import retrofit2.Response
import kotlin.math.log

class RecyclerAdapter(val context: Context, var item: List<Item>):Adapter<RecyclerAdapter.VH>() {

    inner class VH(val binding: RecyclerItemListFragmentBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater=LayoutInflater.from(context)
        val binding=RecyclerItemListFragmentBinding.inflate(layoutInflater,parent,false)
        return VH(binding)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val items= item[position]


        holder.binding.tvCnt.text=("예측질병건수 : ${items.cnt}").toString()
        holder.binding.tvDisscd.text= ("질병명 : ${items.dissCd}")
        //질병명 크게 4분류 카테고리 숫자에서 글씨로 변경
        if (items.dissCd=="1"){
            holder.binding.tvDisscd.text="질병명 : 감기"
        }else {
            if (items.dissCd == "2") {
                holder.binding.tvDisscd.text = "질병명 : 눈병"
            } else {
                if (items.dissCd == "3") {
                    holder.binding.tvDisscd.text = "질병명 : 식중독"

                } else {
                    if (items.dissCd == "4")
                        holder.binding.tvDisscd.text = "질병명 : 피부염"
                }
            }
        }

        //질병위험도 4분류
        holder.binding.tvRisk.text=("질병위험도 : ${items.risk}").toString()
        if (items.risk==1){
            holder.binding.tvRisk.text="질병위험도 : 관심"
        }else{
            if (items.risk==2){
                holder.binding.tvRisk.text="질병위험도 : 주의"
            }else{
                if (items.risk==3){
                    holder.binding.tvRisk.text="질병위험도 : 경고"
                }else {
                    if (items.risk==4){
                        holder.binding.tvRisk.text="질병위험도 : 위험"
                    }
                }
            }

        }

        holder.binding.tvIowrnkzncd.text= ("상세주소 : ${items.lowrnkZnCd}")
        holder.binding.tvZncd.text= ("발생지역 :${items.znCd}")
        holder.binding.tvDt.text= ("예측일자 : ${items.dt }")
        holder.binding.tvDissriskxpln.text= ("행동요령 : ${items.dissRiskXpln }")







    }

}