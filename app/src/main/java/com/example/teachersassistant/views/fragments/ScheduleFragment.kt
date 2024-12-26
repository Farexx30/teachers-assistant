package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachersassistant.adapters.ScheduleRecyclerViewAdapter
import com.example.teachersassistant.common.Day
import com.example.teachersassistant.databinding.FragmentScheduleBinding
import com.example.teachersassistant.dtos.subject.SubjectWithDatesDto
import com.example.teachersassistant.viewmodels.ScheduleViewModel
import java.time.LocalTime

class ScheduleFragment : Fragment() {
    private lateinit var scheduleAdapter: ScheduleRecyclerViewAdapter
    private lateinit var binding: FragmentScheduleBinding

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    private val viewModel: ScheduleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        scheduleAdapter = ScheduleRecyclerViewAdapter(emptyList())

        scheduleAdapter.onItemClickListener = { subject ->
            Toast.makeText(requireActivity(), subject.subjectName, Toast.LENGTH_SHORT).show()
            val action = ScheduleFragmentDirections.actionScheduleFragmentToSubjectInfoFragment(subject.subjectId)
            findNavController().navigate(action)
        }

        binding = FragmentScheduleBinding.inflate(inflater, container, false)
        binding.apply {
            dayScheduleRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = scheduleAdapter
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goToMainMenuFromScheduleButton.setOnClickListener {
            val action = ScheduleFragmentDirections.actionScheduleFragmentToMainMenuFragment()
            findNavController().navigate(action)
        }
    }
}