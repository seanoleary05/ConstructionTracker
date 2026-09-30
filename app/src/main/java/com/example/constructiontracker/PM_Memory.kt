package com.example.constructiontracker

import java.util.concurrent.atomic.AtomicLong

class PlacemarkerMemStore {
    private val placemarks = ArrayList<PMConstructionData>()
    private val lastId = AtomicLong(0L)

    fun findAll(): List<PMConstructionData> {
        return placemarks
    }

    fun create(placemark: PMConstructionData) {
        placemark.id = lastId.incrementAndGet()
        placemarks.add(placemark)
    }

    fun update(placemark: PMConstructionData): Boolean {
        val foundPlacemark = findOne(placemark.id)
        return if (foundPlacemark != null) {
            foundPlacemark.title = placemark.title
            foundPlacemark.desc = placemark.desc
            foundPlacemark.x = placemark.x
            foundPlacemark.y = placemark.y
            true
        } else {
            false
        }
    }

    fun delete(id: Long): Boolean {
        val foundPlacemark = findOne(id)
        return if (foundPlacemark != null) {
            placemarks.remove(foundPlacemark)
            true
        } else {
            false
        }
    }

    fun findOne(id: Long): PMConstructionData? {
        return placemarks.find { p -> p.id == id }
    }
}