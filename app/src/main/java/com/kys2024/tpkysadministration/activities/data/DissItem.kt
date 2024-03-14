package com.kys2024.tpkysadministration.activities.data

data class DissItem(var documents:List<Item>)

//data class DissItemResponse(var header: ResHeader, var body: ResBody)
//
//data class ResHeader(var resultCod: Int, var resultMsg:String, var type:String)
//data class ResBody(var items: List<Item>)

data class Item(
    var dissCd:String,
    var dt:String,
    var znCd:String,
    var lowrnkZnCd:String,
    var cnt:Int,
    var risk:Int,
    var dissRiskXpln:String)