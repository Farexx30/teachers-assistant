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
import com.example.teachersassistant.AssignStudentsToSubjectRecyclerViewAdapter
import com.example.teachersassistant.viewmodels.AssignStudentsToSubjectViewModel
import com.example.teachersassistant.R
import com.example.teachersassistant.StudentsRecyclerViewAdapter
import com.example.teachersassistant.databinding.FragmentAllStudentsBinding
import com.example.teachersassistant.databinding.FragmentAssignStudentsToSubjectBinding
import com.example.teachersassistant.databinding.FragmentSubjectInfoBinding
import com.example.teachersassistant.dtos.StudentDto

class AssignStudentsToSubjectFragment : Fragment() {
    private var students: MutableList<StudentDto> = mutableListOf()
    private lateinit var assignStudentsToSubjectAdapter: AssignStudentsToSubjectRecyclerViewAdapter
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
        loadTestData()

        assignStudentsToSubjectAdapter = AssignStudentsToSubjectRecyclerViewAdapter(students)

        assignStudentsToSubjectAdapter.onItemClickListener = { student ->
            Toast.makeText(requireActivity(), "${student.firstName} ${student.lastName}", Toast.LENGTH_SHORT).show()
        }

        binding = FragmentAssignStudentsToSubjectBinding.inflate(inflater, container, false)
        binding.apply {
            studentsToAssignRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = assignStudentsToSubjectAdapter
            }
        }

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
    private fun loadTestData() {
        students.add(StudentDto(1, "Leoś","Messi", "334528"))
        students.add(StudentDto(2, "Leoś","Messi", "334598"))
        students.add(StudentDto(3, "ktos","Kktosssss", "307083"))
        students.add(StudentDto(4, "UwU","Enjoyer", "333987"))
        students.add(StudentDto(5, "Roni","Messi", "334528"))
        students.add(StudentDto(6, "LeośXDD","Messi", "334528"))
        students.add(StudentDto(7, "Leoś","MessiHAHAHA", "334528"))
        students.add(StudentDto(8, "Leoś","MessHEHEHEi", "334528"))
        students.add(StudentDto(9, "CRISTIANO","RONALDO", "334528"))
        students.add(StudentDto(10, "CRISTIANO","RONALDO", "334528"))
        students.add(StudentDto(11, "CRISTIANO","RONALDO", "334528"))
        students.add(StudentDto(12, "CRISTIANO","RONALDO", "334528"))
        students.add(StudentDto(13, "CRISTIANO","RONALDO", "334528"))
        students.add(StudentDto(14, "some","one", "334528"))
        students.add(StudentDto(15, "some","two", "334528"))
        students.add(StudentDto(16, "some","three", "334528"))
    }

}