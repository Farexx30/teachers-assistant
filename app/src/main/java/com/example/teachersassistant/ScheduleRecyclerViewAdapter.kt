package com.example.teachersassistant

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.databinding.RecyclerViewElementScheduleBinding
import com.example.teachersassistant.dtos.SubjectDto

class ScheduleRecyclerViewAdapter(private val items: MutableList<SubjectDto>)
    : RecyclerView.Adapter<ScheduleRecyclerViewAdapter.ViewHolder>() {

    var onItemClickListener: ((SubjectDto) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScheduleRecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewElementScheduleBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ScheduleRecyclerViewAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: RecyclerViewElementScheduleBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: SubjectDto) {
            binding.apply {
                subjectNameTextView.text = item.name
                subjectHoursTextView.text = "${item.startHour} - ${item.endHour}"
            }

            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}