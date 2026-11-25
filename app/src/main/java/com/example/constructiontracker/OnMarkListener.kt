package com.example.constructiontracker


interface OnMarkListener {

    fun onMarkClick(mark: PMConstructionData)

    fun onMarkLongClick(mark: PMConstructionData): Boolean

}

