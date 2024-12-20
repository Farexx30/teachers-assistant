package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachersassistant.R
import com.example.teachersassistant.SubjectDatesRecyclerViewAdapter
import com.example.teachersassistant.common.Day
import com.example.teachersassistant.databinding.FragmentSubjectInfoBinding
import com.example.teachersassistant.dtos.SubjectDto
import com.example.teachersassistant.viewmodels.SubjectInfoViewModel
import java.time.LocalTime

class SubjectInfoFragment : Fragment() {
    val args: SubjectInfoFragmentArgs by navArgs()

    private var subjects: MutableList<SubjectDto> = mutableListOf()
    private lateinit var subjectDatesAdapter: SubjectDatesRecyclerViewAdapter
    private lateinit var binding: FragmentSubjectInfoBinding

    companion object {
        fun newInstance() = SubjectInfoFragment()
    }

    private val viewModel: SubjectInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadTestData()

        subjectDatesAdapter = SubjectDatesRecyclerViewAdapter(subjects)

        subjectDatesAdapter.onItemClickListener = { subject ->
            Toast.makeText(requireActivity(), "${subject.name} ${subject.day.asString} ${subject.startHour} - ${subject.endHour}", Toast.LENGTH_SHORT).show()
        }

        binding = FragmentSubjectInfoBinding.inflate(inflater, container, false)
        binding.apply {
            subjectDatesRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = subjectDatesAdapter
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goToScheduleFromSubjectInfoButton.setOnClickListener {
            findNavController().navigate(R.id.action_subjectInfoFragment_to_allSubjectsFragment)
        }

        binding.goToAllSubjectsFromSubjectInfoButton.setOnClickListener {
            findNavController().navigate(R.id.action_subjectInfoFragment_to_scheduleFragment)
        }

        binding.goToAssignedStudentsButton.setOnClickListener {
            findNavController().navigate(R.id.action_subjectInfoFragment_to_subjectStudentsFragment)
        }

        binding.cancelSubjectCreationButton.setOnClickListener {
            findNavController().navigate(R.id.action_subjectInfoFragment_to_allSubjectsFragment)
        }

        binding.deleteSubjectButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun loadTestData() {
        subjects.add(SubjectDto("1", "Subject1", Day.MONDAY, LocalTime.of(13, 45), LocalTime.of(15, 15)))
        subjects.add(SubjectDto("2", "Subject2", Day.TUESDAY, LocalTime.of(13, 42), LocalTime.of(15, 15)))
        subjects.add(SubjectDto("3","Subject3", Day.MONDAY, LocalTime.of(13, 10), LocalTime.of(15, 28)))
        subjects.add(SubjectDto("4","Subject4", Day.FRIDAY, LocalTime.of(13, 45), LocalTime.of(15, 15)))
    }
}