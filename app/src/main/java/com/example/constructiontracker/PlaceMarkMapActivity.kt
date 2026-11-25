package com.example.constructiontracker

import org.osmdroid.views.MapView
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker


class PlaceMarkMapActivity : AppCompatActivity() {

    private lateinit var map: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Configuration.getInstance().userAgentValue = packageName
        setContentView(R.layout.activity_placemark_map)

        map = findViewById(R.id.placemarkMap)
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)

        map.controller.setZoom(15.0)
        map.controller.setCenter(GeoPoint(52.245954, -7.139539))

        findViewById<Button>(R.id.mapReturnButton).setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
        showPlacemarks()   // refresh so edits/deletes show up when you come back
    }

    override fun onPause() {
        map.onPause()
        super.onPause()
    }

    private fun showPlacemarks() {
        map.overlays.clear()   // remove old markers before redrawing

        AppData.placedMarks.findAll().forEach { placemark ->

            val marker = Marker(map).apply {
                position = GeoPoint(placemark.x, placemark.y)
                title = placemark.title
                snippet = placemark.desc
                subDescription = placemark.eircode
                relatedObject = placemark
                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            }

            map.overlays.add(marker)
        }

        map.invalidate()
    }
}
