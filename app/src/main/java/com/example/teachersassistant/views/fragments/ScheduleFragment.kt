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
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.R
import com.example.teachersassistant.ScheduleRecyclerViewAdapter
import com.example.teachersassistant.common.Day
import com.example.teachersassistant.databinding.FragmentScheduleBinding
import com.example.teachersassistant.models.entities.Subject
import com.example.teachersassistant.viewmodels.ScheduleViewModel
import org.jetbrains.annotations.Async.Schedule
import java.time.LocalTime

class ScheduleFragment : Fragment() {
    private lateinit var binding: FragmentScheduleBinding

    private var subjects: MutableList<Subject> = mutableListOf()
    private lateinit var scheduleAdapter: ScheduleRecyclerViewAdapter

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

        scheduleAdapter = ScheduleRecyclerViewAdapter(subjects, ::onItemClick)

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

    private fun onItemClick(subject: Subject) {
        Toast.makeText(requireActivity(), subject.name, Toast.LENGTH_SHORT).show()
    }

    private fun loadTestData() {
        subjects.add(Subject("Subject1", Day.MONDAY, LocalTime.of(13, 45), LocalTime.of(15, 15)))
        subjects.add(Subject("Subject2", Day.MONDAY, LocalTime.of(13, 42), LocalTime.of(15, 15)))
        subjects.add(Subject("Subject3", Day.MONDAY, LocalTime.of(13, 10), LocalTime.of(15, 28)))
        subjects.add(Subject("Subject4", Day.MONDAY, LocalTime.of(13, 45), LocalTime.of(15, 15)))
    }
}