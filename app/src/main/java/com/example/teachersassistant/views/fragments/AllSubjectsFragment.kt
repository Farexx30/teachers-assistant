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
import com.example.teachersassistant.AllSubjectsRecyclerViewAdapter
import com.example.teachersassistant.viewmodels.AllSubjectsViewModel
import com.example.teachersassistant.R
import com.example.teachersassistant.common.Day
import com.example.teachersassistant.databinding.FragmentAllSubjectsBinding
import com.example.teachersassistant.dtos.SubjectDto
import java.time.LocalTime

class AllSubjectsFragment : Fragment() {
    private var subjects: MutableList<SubjectDto> = mutableListOf()
    private lateinit var allSubjectsAdapter: AllSubjectsRecyclerViewAdapter
    private lateinit var binding: FragmentAllSubjectsBinding

    companion object {
        fun newInstance() = AllSubjectsFragment()
    }

    private val viewModel: AllSubjectsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadTestData()

        allSubjectsAdapter = AllSubjectsRecyclerViewAdapter(subjects)

        allSubjectsAdapter.onItemClickListener = { subject ->
            Toast.makeText(requireActivity(), subject.name, Toast.LENGTH_SHORT).show()
            val action = AllSubjectsFragmentDirections.actionAllSubjectsFragmentToSubjectInfoFragment(subject.id)
            findNavController().navigate(action)
        }

        binding = FragmentAllSubjectsBinding.inflate(inflater, container, false)
        binding.apply {
            allSubjectsRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = allSubjectsAdapter
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goToMainMenuFromAllSubjectsButton.setOnClickListener {
            val action = AllSubjectsFragmentDirections.actionAllSubjectsFragmentToMainMenuFragment()
            findNavController().navigate(action)
        }

        binding.addNewSubjectButton.setOnClickListener {
            val action = AllSubjectsFragmentDirections.actionAllSubjectsFragmentToSubjectInfoFragment(null)
            findNavController().navigate(action)
        }
    }

    private fun loadTestData() {
        subjects.add(SubjectDto("1", "Subject1", Day.MONDAY, LocalTime.of(13, 45), LocalTime.of(15, 15)))
        subjects.add(SubjectDto("2", "Subject2", Day.TUESDAY, LocalTime.of(13, 42), LocalTime.of(15, 15)))
        subjects.add(SubjectDto("3","Subject3", Day.MONDAY, LocalTime.of(13, 10), LocalTime.of(15, 28)))
        subjects.add(SubjectDto("4","Subject4", Day.FRIDAY, LocalTime.of(13, 45), LocalTime.of(15, 15)))
    }
}