package com.example.constructiontracker

data class PMConstructionData(
    var id: Long = 0L,
    var title: String = "",
    var description: String = "",
    var eircode: String?,
    var x: Double = 0.0,
    var y: Double = 0.0,


)