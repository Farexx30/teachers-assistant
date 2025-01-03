package com.example.teachersassistant.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.databinding.RecyclerViewElementStudentBinding
import com.example.teachersassistant.dtos.student.StudentDto

class StudentsRecyclerViewAdapter (private var items: MutableList<StudentDto>)
    : RecyclerView.Adapter<StudentsRecyclerViewAdapter.ViewHolder>() {

    var onItemClickListener: ((StudentDto) -> Unit)? = null
    var onItemLongClickListener: ((View, StudentDto, Int) -> Unit)? = null

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

            itemView.setOnLongClickListener {
                val position = adapterPosition
                onItemLongClickListener?.invoke(it, item, position)
                true
            }
        }
    }

    fun itemRemoved(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItems: MutableList<StudentDto>) {
        items = newItems
        notifyDataSetChanged()
    }


}