package com.kys2024.tpkysadministration.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kys2024.tpkysadministration.activities.activity.MainActivity
import com.kys2024.tpkysadministration.databinding.FragmentDiseaseListBinding

class DiseaseListFragment:Fragment() {

    private val binding:FragmentDiseaseListBinding by lazy { FragmentDiseaseListBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onResume() { //얘는 화면 눌르면 새로고침 되듯이 자동으로 데이터를 바로 대입해줌
        super.onResume()

    }



}