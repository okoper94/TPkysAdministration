package com.kys2024.tpkysadministration.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kys2024.tpkysadministration.databinding.FragmentDiseaseMapBinding
import com.kys2024.tpkysadministration.databinding.FragmentDiseaseSearchBinding

class DiseaseSearchFragment:Fragment(){

    private val binding: FragmentDiseaseSearchBinding by lazy { FragmentDiseaseSearchBinding.inflate(layoutInflater) }

    override fun onCreateView( //뷰가 만들기만 하는곳
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //뷰가 만들어지고 난후의 작업
        super.onViewCreated(view, savedInstanceState)



    }

}