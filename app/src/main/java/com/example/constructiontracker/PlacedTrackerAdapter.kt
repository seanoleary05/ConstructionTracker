package com.example.constructiontracker


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView

class PlacedTrackerAdapter(
    private var marks: List<PMConstructionData>,
    private val onEdit: (PMConstructionData) -> Unit,
    private val onDelete: (PMConstructionData) -> Unit
) : RecyclerView.Adapter<PlacedTrackerAdapter.MarkViewHolder>() {

    class MarkViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {




        val titleText: TextView =
            itemView.findViewById(R.id.titleText)

        val descriptionText: TextView =
            itemView.findViewById(R.id.descriptionText)

        val eircodeText: TextView =
            itemView.findViewById(R.id.eircodeText)

        val coordinatesText: TextView =
            itemView.findViewById(R.id.coordinatesText)


        val markImage: ImageView =
            itemView.findViewById(R.id.markImage)

        val editButton: Button =
            itemView.findViewById(R.id.editButton)

        val deleteButton: Button =
            itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarkViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_placed_tracker,
                parent,
                false
            )

        return MarkViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MarkViewHolder,
        position: Int
    ) {

        val mark = marks[position]

        val imageUri = mark.imageUri?.let { Uri.parse(it) }

        holder.titleText.text =
            "${mark.id}: ${mark.title}"

        holder.descriptionText.text =
            mark.desc

        holder.eircodeText.text =
            mark.eircode.take(8)

        holder.coordinatesText.text =
            "X: ${mark.x}, Y: ${mark.y}"


        if (imageUri != null) {
            holder.markImage.setImageURI(imageUri)
        } else {
            holder.markImage.setImageResource(
                android.R.drawable.ic_menu_gallery
            )
        }

        holder.editButton.setOnClickListener {
            onEdit(mark)
        }

        holder.deleteButton.setOnClickListener {
            onDelete(mark)
        }

    }

    override fun getItemCount(): Int {
        return marks.size
    }

    fun updateMarks(newMarks: List<PMConstructionData>) {
        marks = newMarks
        notifyDataSetChanged()
    }
}
