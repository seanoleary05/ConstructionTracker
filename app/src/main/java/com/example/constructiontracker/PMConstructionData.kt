package com.example.constructiontracker

data class PMConstructionData(
    var id: Long = 0L,
    var title: String = "",
    var desc: String = "",
    var eircode: String = "No eircode given",
    var x: Double = 0.0,
    var y: Double = 0.0,


)