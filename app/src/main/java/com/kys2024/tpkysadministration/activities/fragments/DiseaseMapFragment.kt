package com.kys2024.tpkysadministration.activities.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.camera.CameraUpdate
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelLayer
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.mapwidget.InfoWindowOptions
import com.kakao.vectormap.mapwidget.component.GuiLayout
import com.kakao.vectormap.mapwidget.component.GuiText
import com.kakao.vectormap.mapwidget.component.Orientation
import com.kys2024.tpkysadministration.R
import com.kys2024.tpkysadministration.activities.activity.DetailMainActivity
import com.kys2024.tpkysadministration.activities.activity.MainActivity
import com.kys2024.tpkysadministration.activities.data.Place
import com.kys2024.tpkysadministration.activities.data.Responce
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

        binding.mapView.start(mapReadyCallback)




    }
    private val mapReadyCallback: KakaoMapReadyCallback =object : KakaoMapReadyCallback() {
        override fun onMapReady(kakaoMap: KakaoMap) {

            //현재 내 위치를 지도의 중심위치로 설정
            val latitude: Double = (activity as MainActivity).myLocation?.latitude ?: 37.5666
            val longitude: Double = (activity as MainActivity).myLocation?.longitude ?: 126.9782
            val myPos: LatLng = LatLng.from(latitude, longitude)

            //내 위치로 지도 카메라 이동
            val cameraUpdate: CameraUpdate = CameraUpdateFactory.newCenterPosition(myPos, 16)
            kakaoMap.moveCamera(cameraUpdate)

            //내 위치에 대한 마커(라벨) 추가하기
            val labelOptions: LabelOptions =
                LabelOptions.from(myPos).setStyles(R.drawable.ic_mypin)//벡터그래픽 이미지는 안됨
            //라벨이 그려질 레이어 객체 소환
            val labelLayer: LabelLayer = kakaoMap.labelManager!!.layer!!
            //라벨 레이어에 라벨추가
            labelLayer.addLabel(labelOptions)

            kakaoMap.setOnLabelClickListener { kakaoMap, layer, label ->

                label.apply {
                    //정보창 [infoWindow] 보여주기

                    val layout= GuiLayout(Orientation.Vertical)
                    layout.setPadding(16,16,16,16)
                    layout.setBackground(R.drawable.base_msg,true)

                    texts.forEach {
                        val guiText= GuiText(it)
                        guiText.setTextSize(30)
                        guiText.setTextColor(Color.WHITE)
                        layout.addView(guiText)

                    }

                    //[정보창 info window]객체 만들기
                    val options: InfoWindowOptions = InfoWindowOptions.from(position)
                    options.body= layout
                    options.setBodyOffset(0f, -10f)
                    options.setTag(tag)

                    kakaoMap.mapWidgetManager!!.infoWindowLayer.removeAll()
                    kakaoMap.mapWidgetManager!!.infoWindowLayer.addInfoWindow(options)


                }//apply...

            }//label click...
            //정보창 클릭에 반응하기
            kakaoMap.setOnInfoWindowClickListener { kakaoMap, infoWindow, guiId ->
                //장소에 대한 상세 소개 웹페이지를 보여주는 화면으로 이동
                val intent= Intent(requireContext(),DetailMainActivity::class.java)

                //클릭한 장소에 대한 정보를 json 문자열로 변환하여 전달해주기
                val place:Place=infoWindow.tag as Place
                val json:String= Gson().toJson(place)
                intent.putExtra("place",json)

                startActivity(intent)
            }




        }

    }


}