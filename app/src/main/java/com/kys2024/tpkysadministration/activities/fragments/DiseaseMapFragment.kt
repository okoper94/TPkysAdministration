package com.kys2024.tpkysadministration.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.R
import androidx.fragment.app.Fragment
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.camera.CameraUpdate
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelLayer
import com.kakao.vectormap.label.LabelOptions
import com.kys2024.tpkysadministration.activities.activity.MainActivity
import com.kys2024.tpkysadministration.databinding.FragmentDiseaseMapBinding

class DiseaseMapFragment :Fragment(){

    private val binding:FragmentDiseaseMapBinding by lazy { FragmentDiseaseMapBinding.inflate(layoutInflater) }

    override fun onCreateView( //뷰가 만들기만 하는곳
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //뷰가 만들어지고 난후의 작업
        super.onViewCreated(view, savedInstanceState)

        binding.mapView.start(map)



    }
    val map:KakaoMapReadyCallback=object :KakaoMapReadyCallback(){
        override fun onMapReady(p0: KakaoMap) {
            val latitude: Double=(activity as MainActivity).myLocation?.latitude ?:37.5666
            val longitude: Double=(activity as MainActivity).myLocation?.longitude ?:126.9782
            val myPos: LatLng = LatLng.from(latitude,longitude)

            val cameraUpdate:CameraUpdate=CameraUpdateFactory.newCenterPosition(myPos,16)
            p0.moveCamera(cameraUpdate)

            val labelOptions:LabelOptions=LabelOptions.from(myPos).setStyles(com.kys2024.tpkysadministration.R.drawable.ic_mypin)
            val labelLayer:LabelLayer=p0.labelManager!!.layer!!
            labelLayer.addLabel(labelOptions)







        }

    }


}


