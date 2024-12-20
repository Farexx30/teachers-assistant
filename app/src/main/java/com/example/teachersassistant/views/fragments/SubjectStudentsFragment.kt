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
import com.example.teachersassistant.StudentsRecyclerViewAdapter
import com.example.teachersassistant.databinding.FragmentSubjectStudentsBinding
import com.example.teachersassistant.dtos.StudentDto
import com.example.teachersassistant.viewmodels.SubjectStudentsViewModel

class SubjectStudentsFragment : Fragment() {
    private val args: SubjectStudentsFragmentArgs by navArgs()

    private var students: MutableList<StudentDto> = mutableListOf()
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
        loadTestData()

        studentsAdapter = StudentsRecyclerViewAdapter(students)

        studentsAdapter.onItemClickListener = { student ->
            Toast.makeText(requireActivity(), "${student.firstName} ${student.lastName} ${student.albumNumber}", Toast.LENGTH_SHORT).show()

            val action = SubjectStudentsFragmentDirections.actionSubjectStudentsFragmentToSubjectStudentInfoFragment(
                subjectId = args.subjectId,
                studentId = student.id)
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