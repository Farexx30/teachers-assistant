package com.example.teachersassistant

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.databinding.RecyclerViewElementSubjectDateBinding
import com.example.teachersassistant.dtos.SubjectDto

class SubjectDatesRecyclerViewAdapter (private val items: MutableList<SubjectDto>)
    : RecyclerView.Adapter<SubjectDatesRecyclerViewAdapter.ViewHolder>() {

    var onItemClickListener: ((SubjectDto) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubjectDatesRecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewElementSubjectDateBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SubjectDatesRecyclerViewAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: RecyclerViewElementSubjectDateBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: SubjectDto) {
            binding.apply {
                dayTextView.text = item.day.asString
                subjectHoursTextView.text = "${item.startHour} - ${item.endHour}"
            }

            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}