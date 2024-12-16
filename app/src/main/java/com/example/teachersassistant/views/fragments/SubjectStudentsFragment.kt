package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.teachersassistant.R
import com.example.teachersassistant.databinding.FragmentStudentBinding
import com.example.teachersassistant.databinding.FragmentSubjectStudentsBinding
import com.example.teachersassistant.viewmodels.SubjectStudentsViewModel

class SubjectStudentsFragment : Fragment() {
    private lateinit var binding: FragmentSubjectStudentsBinding

    companion object {
        fun newInstance() = SubjectStudentsFragment()
    }

    private val viewModel: SubjectStudentsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubjectStudentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goBackToSubjectInfoFromSubjectsStudentsButton.setOnClickListener {
            findNavController().navigate(R.id.action_subjectStudentsFragment_to_subjectInfoFragment)
        }

        binding.assignNewStudentToSubjectButton.setOnClickListener {
            findNavController().navigate(R.id.action_subjectStudentsFragment_to_assignStudentsToSubjectFragment)
        }
    }
}