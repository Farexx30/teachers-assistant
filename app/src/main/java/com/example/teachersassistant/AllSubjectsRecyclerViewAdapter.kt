package com.example.teachersassistant

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.databinding.RecyclerViewElementSubjectBinding
import com.example.teachersassistant.dtos.SubjectDto

class AllSubjectsRecyclerViewAdapter (private val items: MutableList<SubjectDto>)
    : RecyclerView.Adapter<AllSubjectsRecyclerViewAdapter.ViewHolder>() {

    var onItemClickListener: ((SubjectDto) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllSubjectsRecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewElementSubjectBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AllSubjectsRecyclerViewAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: RecyclerViewElementSubjectBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: SubjectDto) {
            binding.apply {
                subjectNameTextView.text = item.name
            }

            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}