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
import com.example.teachersassistant.adapters.SubjectStudentGradesRecyclerViewAdapter
import com.example.teachersassistant.databinding.FragmentSubjectStudentInfoBinding
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.viewmodels.SubjectStudentInfoViewModel

class SubjectStudentInfoFragment : Fragment() {
    private val args: SubjectStudentInfoFragmentArgs by navArgs()

    private lateinit var subjectStudentGradesAdapter: SubjectStudentGradesRecyclerViewAdapter
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
        subjectStudentGradesAdapter = SubjectStudentGradesRecyclerViewAdapter(emptyList())

        subjectStudentGradesAdapter.onItemClickListener = { student ->
            //Toast.makeText(requireActivity(), "${student.firstName} ${student.lastName} GRADE!", Toast.LENGTH_SHORT).show()

            //TODO: Navigate to gradeFragment with the grade id
        }

        binding = FragmentSubjectStudentInfoBinding.inflate(inflater, container, false)
        binding.apply {
            subjectStudentGradesRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = subjectStudentGradesAdapter
            }
        }

        //TODO: Fetch subject student from database based on args.subjectId and args.studentId


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goBackToSubjectStudentsFromSubjectStudentInfoButton.setOnClickListener {
            val action = SubjectStudentInfoFragmentDirections.actionSubjectStudentInfoFragmentToSubjectStudentsFragment(args.subjectId)
            findNavController().navigate(action)
        }

        binding.newGradeButton.setOnClickListener {
            val action = SubjectStudentInfoFragmentDirections.actionSubjectStudentInfoFragmentToGradeFragment(
                subjectId = args.subjectId,
                studentId = args.studentId,
                gradeId = 0L)
            findNavController().navigate(action)
        }

        //TODO: Click on single grade
    }
}