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
import com.example.teachersassistant.adapters.SubjectDatesRecyclerViewAdapter
import com.example.teachersassistant.common.Day
import com.example.teachersassistant.databinding.FragmentSubjectInfoBinding
import com.example.teachersassistant.dtos.subject.SubjectWithDatesDto
import com.example.teachersassistant.viewmodels.SubjectInfoViewModel
import java.time.LocalTime

class SubjectInfoFragment : Fragment() {
    private val args: SubjectInfoFragmentArgs by navArgs()

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
        subjectDatesAdapter = SubjectDatesRecyclerViewAdapter(emptyList())

        subjectDatesAdapter.onItemClickListener = { subject ->
            //Toast.makeText(requireActivity(), "${subject.name} ${subject.day.asString} ${subject.startHour} - ${subject.endHour}", Toast.LENGTH_SHORT).show()
        }

        binding = FragmentSubjectInfoBinding.inflate(inflater, container, false)
        binding.apply {
            subjectDatesRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = subjectDatesAdapter
            }
        }

        //TODO: Fetch data from database based on args.subjectId

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goToScheduleFromSubjectInfoButton.setOnClickListener {
            val action = SubjectInfoFragmentDirections.actionSubjectInfoFragmentToScheduleFragment()
            findNavController().navigate(action)
        }

        binding.goToAllSubjectsFromSubjectInfoButton.setOnClickListener {
            val action = SubjectInfoFragmentDirections.actionSubjectInfoFragmentToAllSubjectsFragment()
            findNavController().navigate(action)
        }

        binding.goToAssignedStudentsButton.setOnClickListener {
            val action = SubjectInfoFragmentDirections.actionSubjectInfoFragmentToSubjectStudentsFragment(args.subjectId)
            findNavController().navigate(action)
        }

        binding.cancelSubjectCreationButton.setOnClickListener {
            val action = SubjectInfoFragmentDirections.actionSubjectInfoFragmentToAllSubjectsFragment()
            findNavController().navigate(action)
        }

        binding.deleteSubjectButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}