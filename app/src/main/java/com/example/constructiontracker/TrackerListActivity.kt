package com.example.constructiontracker


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

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

            listener = object : OnMarkListener {



                override fun onMarkClick(mark: PMConstructionData) {

                    editMark(mark)

                }



                override fun onMarkLongClick(mark: PMConstructionData): Boolean {

                    Timber.d("Long press on mark id=${mark.id}, title=${mark.title}")

                    deleteMark(mark)

                    return true

                }

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

        AlertDialog.Builder(this)
            .setTitle("Delete Confirmation")
            .setMessage("Do you want to delete \"${mark.title}\"?")
            .setNegativeButton("Cancel",null)
            .setPositiveButton("Delete Mark"){_, _ ->
                AppData.placedMarks.delete(mark.id)

                adapter.updateMarks(
                    AppData.placedMarks.findAll()

                )
            }




        .show()
    }

}
