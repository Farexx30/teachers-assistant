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
import com.example.teachersassistant.R
import com.example.teachersassistant.ScheduleRecyclerViewAdapter
import com.example.teachersassistant.common.Day
import com.example.teachersassistant.databinding.FragmentScheduleBinding
import com.example.teachersassistant.dtos.SubjectDto
import com.example.teachersassistant.viewmodels.ScheduleViewModel
import java.time.LocalTime

class ScheduleFragment : Fragment() {
    private var subjects: MutableList<SubjectDto> = mutableListOf()
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
        loadTestData()

        scheduleAdapter = ScheduleRecyclerViewAdapter(subjects)

        scheduleAdapter.onItemClickListener = { subject ->
            Toast.makeText(requireActivity(), subject.name, Toast.LENGTH_SHORT).show()
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
            findNavController().navigate(R.id.action_scheduleFragment_to_mainMenuFragment)
        }
    }

    private fun loadTestData() {
        subjects.add(SubjectDto("1", "Subject1", Day.MONDAY, LocalTime.of(13, 45), LocalTime.of(15, 15)))
        subjects.add(SubjectDto("2", "Subject2", Day.TUESDAY, LocalTime.of(13, 42), LocalTime.of(15, 15)))
        subjects.add(SubjectDto("3","Subject3", Day.MONDAY, LocalTime.of(13, 10), LocalTime.of(15, 28)))
        subjects.add(SubjectDto("4","Subject4", Day.FRIDAY, LocalTime.of(13, 45), LocalTime.of(15, 15)))
    }
}