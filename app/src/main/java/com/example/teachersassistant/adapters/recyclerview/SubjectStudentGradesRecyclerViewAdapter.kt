package com.example.teachersassistant.adapters.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.databinding.RecyclerViewElementGradeBinding
import com.example.teachersassistant.dtos.subjectstudentgrade.SubjectStudentGradeDto

class SubjectStudentGradesRecyclerViewAdapter (private var items: MutableList<SubjectStudentGradeDto>)
    : RecyclerView.Adapter<SubjectStudentGradesRecyclerViewAdapter.ViewHolder>() {

    var onItemClickListener: ((SubjectStudentGradeDto) -> Unit)? = null
    var onItemLongClickListener: ((View, SubjectStudentGradeDto, Int) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewElementGradeBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: RecyclerViewElementGradeBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: SubjectStudentGradeDto) {
            binding.apply {
                gradeTitleTextView.text = item.title
                gradeTextView.text = item.grade.toString()
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
    fun fillWithData(newItems: MutableList<SubjectStudentGradeDto>) {
        items = newItems
        notifyDataSetChanged()
    }
}