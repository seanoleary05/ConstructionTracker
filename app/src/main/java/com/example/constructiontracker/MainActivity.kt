package com.example.constructiontracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainTitle)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val listTrackersButton =
            findViewById<Button>(R.id.listTrackerButton)

        val addTrackerButton =
            findViewById<Button>(R.id.addTrackerButton)

        listTrackersButton.setOnClickListener {
            startActivity(
                Intent(this, TrackerListActivity::class.java)
            )
        }

        addTrackerButton.setOnClickListener {
            startActivity(
                Intent(this, AddEditActivity::class.java)
            )
        }

    }
}
