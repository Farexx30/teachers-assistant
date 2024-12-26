package com.example.teachersassistant.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.databinding.RecyclerViewElementStudentBinding
import com.example.teachersassistant.dtos.student.StudentDto

class StudentsRecyclerViewAdapter (private var items: List<StudentDto>)
    : RecyclerView.Adapter<StudentsRecyclerViewAdapter.ViewHolder>() {

    var onItemClickListener: ((StudentDto) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewElementStudentBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
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
                studentAlbumNumberTextView.text = "Album number: ${item.albumNumber}"
            }

            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }

    fun updateData(newItems: List<StudentDto>) {
        items = newItems
        notifyDataSetChanged()
    }
}