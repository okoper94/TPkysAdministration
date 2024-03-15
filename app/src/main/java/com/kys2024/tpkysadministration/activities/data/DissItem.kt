package com.kys2024.tpkysadministration.activities.data

import retrofit2.Response

data class All(var response:Responce)
data class Responce(var header:Header,val body:Body)
data class Header(var resultCode:Int,var resultMsg:String,var type:String)
data class Body(var items:List<Item>)
data class Item(
    var dissCd:String,
    var dt:String,
    var znCd:String,
    var lowrnkZnCd:String,
    var cnt:Int,
    var risk:Int,
    var dissRiskXpln:String
)



//
//data class DissItem(var documents:List<Item>)
//
//data class DissItemResponse(var header: ResHeader, var body: ResBody)
//
//data class ResHeader(var resultCod: Int, var resultMsg:String, var type:String)
//data class ResBody(var items: List<Item>)
//
//data class Item(
//    var dissCd:String,
//    var dt:String,
//    var znCd:String,
//    var lowrnkZnCd:String,
//    var cnt:Int,
//    var risk:Int,
//    var dissRiskXpln:String)
//
