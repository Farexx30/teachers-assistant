package com.example.teachersassistant

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.databinding.RecyclerViewElementGradeBinding
import com.example.teachersassistant.dtos.StudentDto

class SubjectStudentGradesRecyclerViewAdapter (private val items: MutableList<StudentDto>)
    : RecyclerView.Adapter<SubjectStudentGradesRecyclerViewAdapter.ViewHolder>() {

    var onItemClickListener: ((StudentDto) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubjectStudentGradesRecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewElementGradeBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SubjectStudentGradesRecyclerViewAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: RecyclerViewElementGradeBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: StudentDto) {
            binding.apply {
                gradeTitleTextView.text = "Test title"
                gradeTextView.text = "4.5"
            }

            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}