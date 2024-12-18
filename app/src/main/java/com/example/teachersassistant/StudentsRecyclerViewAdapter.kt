package com.example.teachersassistant

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.databinding.RecyclerViewElementStudentBinding
import com.example.teachersassistant.dtos.StudentDto

class StudentsRecyclerViewAdapter (private val items: MutableList<StudentDto>)
    : RecyclerView.Adapter<StudentsRecyclerViewAdapter.ViewHolder>() {

    var onItemClickListener: ((StudentDto) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentsRecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewElementStudentBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: StudentsRecyclerViewAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: RecyclerViewElementStudentBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: StudentDto) {
            binding.apply {
                studentFullNameTextView.text = "${item.firstName} ${item.lastName}"
            }

            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}