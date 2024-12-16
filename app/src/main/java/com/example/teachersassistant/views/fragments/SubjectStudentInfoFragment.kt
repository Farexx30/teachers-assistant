package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.teachersassistant.R
import com.example.teachersassistant.databinding.FragmentSubjectStudentInfoBinding
import com.example.teachersassistant.databinding.FragmentSubjectStudentsBinding
import com.example.teachersassistant.viewmodels.SubjectStudentInfoViewModel

class SubjectStudentInfoFragment : Fragment() {
    private lateinit var binding: FragmentSubjectStudentInfoBinding

    companion object {
        fun newInstance() = SubjectStudentInfoFragment()
    }

    private val viewModel: SubjectStudentInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubjectStudentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goBackToSubjectStudentsFromSubjectStudentInfoButton.setOnClickListener {
            findNavController().navigate(R.id.action_subjectStudentInfoFragment_to_subjectStudentsFragment)
        }

        binding.newGradeButton.setOnClickListener {
            findNavController().navigate(R.id.action_subjectStudentInfoFragment_to_gradeFragment)
        }

        //TODO: Click on single grade
    }
}