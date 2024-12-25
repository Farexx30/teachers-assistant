package com.example.teachersassistant.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.databinding.RecyclerViewElementSubjectDateBinding
import com.example.teachersassistant.dtos.subject.SubjectWithDatesDto

class SubjectDatesRecyclerViewAdapter (private val items: List<SubjectWithDatesDto>)
    : RecyclerView.Adapter<SubjectDatesRecyclerViewAdapter.ViewHolder>() {

    var onItemClickListener: ((SubjectWithDatesDto) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewElementSubjectDateBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: RecyclerViewElementSubjectDateBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: SubjectWithDatesDto) {
            binding.apply {
//                dayTextView.text = item.subjectDay.asString
//                subjectHoursTextView.text = "${item.subjectStartHour} - ${item.subjectEndHour}"
            }

            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}