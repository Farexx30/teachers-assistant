package com.example.teachersassistant.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.databinding.RecyclerViewElementSubjectBinding
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.dtos.subject.SubjectBasicInfoDto

class AllSubjectsRecyclerViewAdapter (private var items: MutableList<SubjectBasicInfoDto>)
    : RecyclerView.Adapter<AllSubjectsRecyclerViewAdapter.ViewHolder>() {

    var onItemClickListener: ((SubjectBasicInfoDto) -> Unit)? = null
    var onItemLongClickListener: ((View, SubjectBasicInfoDto, Int) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewElementSubjectBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: RecyclerViewElementSubjectBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: SubjectBasicInfoDto) {
            binding.apply {
                subjectNameTextView.text = item.name
            }

            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }

            itemView.setOnLongClickListener {
                onItemLongClickListener?.invoke(it, item, adapterPosition)
                true
            }
        }
    }

    fun itemRemoved(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fillWithData(newItems: MutableList<SubjectBasicInfoDto>) {
        items = newItems
        notifyDataSetChanged()
    }
}