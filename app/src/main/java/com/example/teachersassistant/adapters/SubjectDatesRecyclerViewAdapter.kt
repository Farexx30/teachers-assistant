package com.example.teachersassistant.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.databinding.RecyclerViewElementSubjectDateBinding
import com.example.teachersassistant.dtos.subject.SubjectBasicInfoDto
import com.example.teachersassistant.dtos.subject.SubjectDateDto
import com.example.teachersassistant.dtos.subject.SubjectWithDatesDto

class SubjectDatesRecyclerViewAdapter (private var items: List<SubjectDateDto>)
    : RecyclerView.Adapter<SubjectDatesRecyclerViewAdapter.ViewHolder>() {

    var onItemClickListener: ((SubjectDateDto) -> Unit)? = null


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
        fun bind(item: SubjectDateDto) {
            binding.apply {
                dayTextView.text = item.day.asString
                subjectHoursTextView.text = "${item.startHour} - ${item.endHour}"
            }

            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }

    fun updateData(newItems: List<SubjectDateDto>) {
        items = newItems
        notifyDataSetChanged()
    }
}