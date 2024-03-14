package com.kys2024.tpkysadministration.activities.network

import com.kys2024.tpkysadministration.activities.data.DissItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

//    @GET("/B550928/dissForecastInfoSvc/getDissForecastInfo?")
//    fun getDissItem(@Query("serviceKey")serviceKey:String, @Query("numOfRows")numOfRows:String, @Query("pageNo")pageNo:String, @Query("type")type:String, @Query("dissCd")dissCd:String,@Query("znCd")znCd:String):Call<String>



      @GET("/B550928/dissForecastInfoSvc/getDissForecastInfo")
      fun getDissItem(@Query("serviceKey") serviceKey:String,@Query("numOfRows") numOfRows:String,@Query("pageNo") pageNo:String,@Query("type") type:String,@Query("dissCd") dissCd:String,@Query("znCd") znCd:String ):Call<DissItem>



}