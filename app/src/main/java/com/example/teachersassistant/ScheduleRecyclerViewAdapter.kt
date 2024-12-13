package com.example.teachersassistant

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.databinding.RecyclerViewElementScheduleBinding
import com.example.teachersassistant.models.entities.Subject

class ScheduleRecyclerViewAdapter(
    private val items: MutableList<Subject>,
    private val onItemLongClick: (Subject) -> Unit
) : RecyclerView.Adapter<ScheduleRecyclerViewAdapter.ViewHolder>() {

    private lateinit var binding: RecyclerViewElementScheduleBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScheduleRecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = RecyclerViewElementScheduleBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ScheduleRecyclerViewAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: RecyclerViewElementScheduleBinding): RecyclerView.ViewHolder(itemView.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Subject) {
            binding.apply {
                subjectNameTextView.text = item.name
                subjectHoursTextView.text = "${item.startHour} - ${item.endHour}"
            }

            itemView.setOnLongClickListener {
                onItemLongClick(item)
                true
            }
        }
    }
}