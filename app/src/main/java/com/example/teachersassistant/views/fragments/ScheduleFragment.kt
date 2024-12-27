package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachersassistant.adapters.ScheduleRecyclerViewAdapter
import com.example.teachersassistant.common.Day
import com.example.teachersassistant.databinding.FragmentScheduleBinding
import com.example.teachersassistant.dtos.subject.SubjectWithDatesDto
import com.example.teachersassistant.viewmodels.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalTime

@AndroidEntryPoint
class ScheduleFragment : Fragment() {
    private lateinit var scheduleAdapter: ScheduleRecyclerViewAdapter
    private lateinit var binding: FragmentScheduleBinding

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    private val viewModel: ScheduleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        scheduleAdapter = ScheduleRecyclerViewAdapter(emptyList())

        scheduleAdapter.onItemClickListener = { subject ->
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

        lifecycleScope.launch {
            viewModel.subjects.collect { subjects ->
                scheduleAdapter.updateData(subjects)
            }
        }

        binding.goToMainMenuFromScheduleButton.setOnClickListener {
            val action = ScheduleFragmentDirections.actionScheduleFragmentToMainMenuFragment()
            findNavController().navigate(action)
        }

        binding.mondayButton.setOnClickListener {
            viewModel.getSubjectsByDay("Monday")
        }

        binding.tuesdayButton.setOnClickListener {
            viewModel.getSubjectsByDay("Tuesday")
        }

        binding.wednesdayButton.setOnClickListener {
            viewModel.getSubjectsByDay("Wednesday")
        }

        binding.thursdayButton.setOnClickListener {
            viewModel.getSubjectsByDay("Thursday")
        }

        binding.fridayButton.setOnClickListener {
            viewModel.getSubjectsByDay("Friday")
        }

        binding.saturdayButton.setOnClickListener {
            viewModel.getSubjectsByDay("Saturday")
        }

        binding.sundayButton.setOnClickListener {
            viewModel.getSubjectsByDay("Sunday")
        }
    }
}