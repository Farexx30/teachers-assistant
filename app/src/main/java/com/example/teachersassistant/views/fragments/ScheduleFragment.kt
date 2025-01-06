package com.example.teachersassistant.views.fragments

import android.graphics.Color
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachersassistant.R
import com.example.teachersassistant.adapters.ScheduleRecyclerViewAdapter
import com.example.teachersassistant.common.Day
import com.example.teachersassistant.common.toTitleCase
import com.example.teachersassistant.databinding.FragmentScheduleBinding
import com.example.teachersassistant.dtos.subject.SubjectWithDatesDto
import com.example.teachersassistant.viewmodels.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

@AndroidEntryPoint
class ScheduleFragment : Fragment() {
    private lateinit var scheduleAdapter: ScheduleRecyclerViewAdapter
    private lateinit var binding: FragmentScheduleBinding

    private lateinit var currentDayButton: Button
    private lateinit var currentDay: String //Only necessary for initialization

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    private val viewModel: ScheduleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentDay = LocalDate.now().dayOfWeek.name.toTitleCase()
        viewModel.getSubjectsByDay(currentDay)
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

        lifecycleScope.launch {
            viewModel.subjects.collect { subjects ->
                scheduleAdapter.fillWithData(subjects.toList())
            }
        }

        binding = FragmentScheduleBinding.inflate(inflater, container, false)
        binding.scheduleViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
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


        binding.mondayButton.setOnClickListener {
            viewModel.getSubjectsByDay(Day.MONDAY.asString)

            changeCurrentDayButton(binding.mondayButton)
        }

        binding.tuesdayButton.setOnClickListener {
            viewModel.getSubjectsByDay(Day.TUESDAY.asString)

            changeCurrentDayButton(binding.tuesdayButton)
        }

        binding.wednesdayButton.setOnClickListener {
            viewModel.getSubjectsByDay(Day.WEDNESDAY.asString)

            changeCurrentDayButton(binding.wednesdayButton)
        }

        binding.thursdayButton.setOnClickListener {
            viewModel.getSubjectsByDay(Day.THURSDAY.asString)

            changeCurrentDayButton(binding.thursdayButton)
        }

        binding.fridayButton.setOnClickListener {
            viewModel.getSubjectsByDay(Day.FRIDAY.asString)

            changeCurrentDayButton(binding.fridayButton)
        }

        binding.saturdayButton.setOnClickListener {
            viewModel.getSubjectsByDay(Day.SATURDAY.asString)

            changeCurrentDayButton(binding.saturdayButton)
        }

        binding.sundayButton.setOnClickListener {
            viewModel.getSubjectsByDay(Day.SUNDAY.asString)

            changeCurrentDayButton(binding.sundayButton)
        }

        currentDayButton = when {
            currentDay.equals(Day.MONDAY.asString, ignoreCase = true) -> binding.mondayButton
            currentDay.equals(Day.TUESDAY.asString, ignoreCase = true) -> binding.tuesdayButton
            currentDay.equals(Day.WEDNESDAY.asString, ignoreCase = true) -> binding.wednesdayButton
            currentDay.equals(Day.THURSDAY.asString, ignoreCase = true) -> binding.thursdayButton
            currentDay.equals(Day.FRIDAY.asString, ignoreCase = true) -> binding.fridayButton
            currentDay.equals(Day.SATURDAY.asString, ignoreCase = true) -> binding.saturdayButton
            currentDay.equals(Day.SUNDAY.asString, ignoreCase = true) -> binding.sundayButton
            else -> null!! // Not possible
        }
        currentDayButton.apply {
            isEnabled = false
            alpha = 0.5F
        }
    }

    private fun changeCurrentDayButton(newCurrentDayButton: Button) {
        //Enable old current day button:
        currentDayButton.apply {
            isEnabled = true
            alpha = 1.0F
        }

        currentDayButton = newCurrentDayButton

        //Disable new current day button:
        currentDayButton.apply {
            isEnabled = false
            alpha = 0.5F
        }
    }
}