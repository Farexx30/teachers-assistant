package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.teachersassistant.R
import com.example.teachersassistant.databinding.FragmentAllSubjectsBinding
import com.example.teachersassistant.databinding.FragmentSubjectInfoBinding
import com.example.teachersassistant.viewmodels.SubjectInfoViewModel

class SubjectInfoFragment : Fragment() {
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
        binding = FragmentSubjectInfoBinding.inflate(inflater, container, false)
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
}