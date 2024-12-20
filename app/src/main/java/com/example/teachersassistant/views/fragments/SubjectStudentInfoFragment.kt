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
import com.example.teachersassistant.R
import com.example.teachersassistant.SubjectStudentGradesRecyclerViewAdapter
import com.example.teachersassistant.databinding.FragmentSubjectStudentInfoBinding
import com.example.teachersassistant.dtos.StudentDto
import com.example.teachersassistant.viewmodels.SubjectStudentInfoViewModel

class SubjectStudentInfoFragment : Fragment() {
    private var students: MutableList<StudentDto> = mutableListOf()
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
        loadTestData()

        subjectStudentGradesAdapter = SubjectStudentGradesRecyclerViewAdapter(students)

        subjectStudentGradesAdapter.onItemClickListener = { student ->
            Toast.makeText(requireActivity(), "${student.firstName} ${student.lastName} GRADE!", Toast.LENGTH_SHORT).show()
        }

        binding = FragmentSubjectStudentInfoBinding.inflate(inflater, container, false)
        binding.apply {
            subjectStudentGradesRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = subjectStudentGradesAdapter
            }
        }

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

    private fun loadTestData() {
        students.add(StudentDto("1", "Leoś","Messi", "334528"))
        students.add(StudentDto("2", "Leoś","Messi", "334598"))
        students.add(StudentDto("3", "ktos","Kktosssss", "307083"))
        students.add(StudentDto("4", "UwU","Enjoyer", "333987"))
        students.add(StudentDto("5", "Roni","Messi", "334528"))
        students.add(StudentDto("6", "LeośXDD","Messi", "334528"))
        students.add(StudentDto("7", "Leoś","MessiHAHAHA", "334528"))
        students.add(StudentDto("8", "Leoś","MessHEHEHEi", "334528"))
        students.add(StudentDto("9", "CRISTIANO","RONALDO", "334528"))
        students.add(StudentDto("10", "CRISTIANO","RONALDO", "334528"))
        students.add(StudentDto("11", "CRISTIANO","RONALDO", "334528"))
        students.add(StudentDto("12", "CRISTIANO","RONALDO", "334528"))
        students.add(StudentDto("13", "CRISTIANO","RONALDO", "334528"))
        students.add(StudentDto("14", "some","one", "334528"))
        students.add(StudentDto("15", "some","two", "334528"))
        students.add(StudentDto("16", "some","three", "334528"))
    }
}