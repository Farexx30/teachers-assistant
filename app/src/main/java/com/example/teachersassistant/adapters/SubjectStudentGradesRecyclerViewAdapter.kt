package com.example.teachersassistant.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.databinding.RecyclerViewElementGradeBinding
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.dtos.student.StudentWithGradesDto
import com.example.teachersassistant.dtos.student.SubjectStudentGradeDto
import com.example.teachersassistant.dtos.subject.SubjectBasicInfoDto

class SubjectStudentGradesRecyclerViewAdapter (private var items: List<SubjectStudentGradeDto>)
    : RecyclerView.Adapter<SubjectStudentGradesRecyclerViewAdapter.ViewHolder>() {

    var onItemClickListener: ((SubjectStudentGradeDto) -> Unit)? = null


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
        }
    }

    fun updateData(newItems: List<SubjectStudentGradeDto>) {
        items = newItems
        notifyDataSetChanged()
    }
}