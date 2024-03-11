package com.kys2024.tpkysadministration.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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



}