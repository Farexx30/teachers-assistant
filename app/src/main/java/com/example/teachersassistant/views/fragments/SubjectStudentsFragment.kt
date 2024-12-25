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
import com.example.teachersassistant.adapters.StudentsRecyclerViewAdapter
import com.example.teachersassistant.databinding.FragmentSubjectStudentsBinding
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.viewmodels.SubjectStudentsViewModel

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

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        studentsAdapter = StudentsRecyclerViewAdapter(emptyList())

        studentsAdapter.onItemClickListener = { student ->
            Toast.makeText(requireActivity(), "${student.firstName} ${student.lastName} ${student.albumNumber}", Toast.LENGTH_SHORT).show()

            val action = SubjectStudentsFragmentDirections.actionSubjectStudentsFragmentToSubjectStudentInfoFragment(
                subjectId = args.subjectId,
                studentId = student.id.toString())
            findNavController().navigate(action)
        }

        binding = FragmentSubjectStudentsBinding.inflate(inflater, container, false)
        binding.apply {
            subjectStudentsRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = studentsAdapter
            }
        }

        //TODO: Fetch subject students from database based on args.subjectId

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