package com.example.constructiontracker


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TrackerListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlacedTrackerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tracker_list)

        recyclerView =
            findViewById(R.id.trackersRecyclerview)

        adapter = PlacedTrackerAdapter(
            marks = AppData.placedMarks.findAll(),

            onEdit = { mark ->
                editMark(mark)
            },

            onDelete = { mark ->
                deleteMark(mark)
            }
        )

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        recyclerView.adapter = adapter

        val returnButton =
            findViewById<Button>(R.id.TC_Return)

        returnButton.setOnClickListener {

            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
    }

    override fun onResume() {
        super.onResume()

        if (::adapter.isInitialized) {

            adapter.updateMarks(
                AppData.placedMarks.findAll()
            )
        }
    }

    private fun editMark(mark: PMConstructionData) {

        val intent =
            Intent(this, AddEditActivity::class.java)

        intent.putExtra("id", mark.id)

        startActivity(intent)
    }

    private fun deleteMark(mark: PMConstructionData) {

        AppData.placedMarks.delete(mark.id)

        adapter.updateMarks(
            AppData.placedMarks.findAll()
        )
    }
}
