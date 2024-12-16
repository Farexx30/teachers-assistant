package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.teachersassistant.viewmodels.AssignStudentsToSubjectViewModel
import com.example.teachersassistant.R
import com.example.teachersassistant.databinding.FragmentAssignStudentsToSubjectBinding
import com.example.teachersassistant.databinding.FragmentSubjectInfoBinding

class AssignStudentsToSubjectFragment : Fragment() {
    private lateinit var binding: FragmentAssignStudentsToSubjectBinding

    companion object {
        fun newInstance() = AssignStudentsToSubjectFragment()
    }

    private val viewModel: AssignStudentsToSubjectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAssignStudentsToSubjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.assignChosenStudentsToSubjectButton.setOnClickListener {
            findNavController().navigate(R.id.action_assignStudentsToSubjectFragment_to_subjectStudentsFragment)
        }

        binding.cancelStudentAssignmentToSubjectButton.setOnClickListener {
            findNavController().navigate(R.id.action_assignStudentsToSubjectFragment_to_subjectStudentsFragment)
        }

        //TODO: Students selection
    }
}