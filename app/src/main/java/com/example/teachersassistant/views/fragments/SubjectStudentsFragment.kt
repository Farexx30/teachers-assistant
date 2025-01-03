package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachersassistant.adapters.StudentsRecyclerViewAdapter
import com.example.teachersassistant.databinding.FragmentSubjectStudentsBinding
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.viewmodels.SubjectStudentsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SubjectStudentsFragment : Fragment() {
    private val args: SubjectStudentsFragmentArgs by navArgs()

    private lateinit var studentsAdapter: StudentsRecyclerViewAdapter
    private lateinit var binding: FragmentSubjectStudentsBinding

    companion object {
        fun newInstance() = SubjectStudentsFragment()
    }

    private val viewModel: SubjectStudentsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getSubjectStudentsBySubjectId(args.subjectId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        studentsAdapter = StudentsRecyclerViewAdapter(mutableListOf())

        studentsAdapter.onItemClickListener = { student ->
            val action = SubjectStudentsFragmentDirections.actionSubjectStudentsFragmentToSubjectStudentInfoFragment(
                subjectId = args.subjectId,
                studentId = student.id)
            findNavController().navigate(action)
        }

        binding = FragmentSubjectStudentsBinding.inflate(inflater, container, false)
        binding.apply {
            subjectStudentsRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = studentsAdapter
            }
        }

        lifecycleScope.launch {
            viewModel.students.collect { students ->
                studentsAdapter.updateData(students.toMutableList())
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goBackToSubjectInfoFromSubjectsStudentsButton.setOnClickListener {
            val action = SubjectStudentsFragmentDirections.actionSubjectStudentsFragmentToSubjectInfoFragment(args.subjectId)
            findNavController().navigate(action)
        }

        binding.assignNewStudentToSubjectButton.setOnClickListener {
            val action = SubjectStudentsFragmentDirections.actionSubjectStudentsFragmentToAssignStudentsToSubjectFragment(args.subjectId)
            findNavController().navigate(action)
        }
    }
}