package com.example.teachersassistant.adapters.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.databinding.RecyclerViewElementSubjectDateBinding
import com.example.teachersassistant.dtos.subject.SubjectDateDto

class SubjectDatesRecyclerViewAdapter (private var items: MutableList<SubjectDateDto>)
    : RecyclerView.Adapter<SubjectDatesRecyclerViewAdapter.ViewHolder>() {

    var onItemClickListener: ((SubjectDateDto) -> Unit)? = null
    var onItemLongClickListener: ((View, SubjectDateDto, Int) -> Unit)? = null


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
    fun fillWithData(newItems: MutableList<SubjectDateDto>) {
        items = newItems
        notifyDataSetChanged()
    }
}