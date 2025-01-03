package com.example.teachersassistant.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.R
import com.example.teachersassistant.databinding.RecyclerViewElementStudentBinding
import com.example.teachersassistant.dtos.student.StudentDto

class AssignStudentsToSubjectRecyclerViewAdapter(private var items: List<StudentDto>)
    : RecyclerView.Adapter<AssignStudentsToSubjectRecyclerViewAdapter.ViewHolder>() {

    private val selectedItems = mutableListOf<StudentDto>()
    var onSelectionChangedListener: ((List<StudentDto>) -> Unit)? = null

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

    inner class ViewHolder(private val binding: RecyclerViewElementStudentBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: StudentDto) {
            binding.apply {
                studentFullNameTextView.text = "${item.firstName} ${item.lastName}"
                studentAlbumNumberTextView.text = "Album number: ${item.albumNumber}"

                root.setBackgroundResource(
                    if (selectedItems.contains(item)) {
                        R.drawable.element_rounded_light_background
                    }
                    else {
                        R.drawable.element_rounded_dark_background
                    }
                )
            }

            itemView.setOnClickListener {
                toggleSelection(item)
            }
        }
    }

    private fun toggleSelection(item: StudentDto) {
        if (selectedItems.contains(item)) {
            selectedItems.remove(item)
        } else {
            selectedItems.add(item)
        }

        val position = items.indexOf(item)
        if (position != -1) {
            notifyItemChanged(position)
        }

        onSelectionChangedListener?.invoke(selectedItems)
    }

    fun updateData(newItems: List<StudentDto>) {
        items = newItems
        selectedItems.clear()
        notifyDataSetChanged()
    }

    fun getSelectedItems(): List<StudentDto> = selectedItems
}