package com.example.teachersassistant.adapters.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.databinding.RecyclerViewElementScheduleBinding
import com.example.teachersassistant.dtos.subject.SubjectAndHoursDto

class ScheduleRecyclerViewAdapter(private var items: List<SubjectAndHoursDto>)
    : RecyclerView.Adapter<ScheduleRecyclerViewAdapter.ViewHolder>() {

    var onItemClickListener: ((SubjectAndHoursDto) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewElementScheduleBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: RecyclerViewElementScheduleBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: SubjectAndHoursDto) {
            binding.apply {
                subjectNameTextView.text = item.subjectName
                subjectHoursTextView.text = "${item.subjectStartHour} - ${item.subjectEndHour}"
            }

            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fillWithData(newItems: List<SubjectAndHoursDto>) {
        items = newItems
        notifyDataSetChanged()
    }
}