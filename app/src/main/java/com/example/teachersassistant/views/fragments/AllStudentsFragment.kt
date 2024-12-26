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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachersassistant.R
import com.example.teachersassistant.adapters.StudentsRecyclerViewAdapter
import com.example.teachersassistant.databinding.FragmentAllStudentsBinding
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.viewmodels.AllStudentsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllStudentsFragment : Fragment() {
    private lateinit var studentsAdapter: StudentsRecyclerViewAdapter
    private lateinit var binding: FragmentAllStudentsBinding

    companion object {
        fun newInstance() = AllStudentsFragment()
    }

    private val viewModel: AllStudentsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        studentsAdapter = StudentsRecyclerViewAdapter(emptyList())

        studentsAdapter.onItemClickListener = { student ->
            val action = AllStudentsFragmentDirections.actionAllStudentsFragmentToStudentFragment(student.id)
            findNavController().navigate(action)
        }

        binding = FragmentAllStudentsBinding.inflate(inflater, container, false)
        binding.allStudentsViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.apply {
            allStudentsRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = studentsAdapter
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.students.collect { students ->
                studentsAdapter.updateData(students)
            }
        }

        binding.goToMainMenuFromAllStudentsButton.setOnClickListener {
            val action = AllStudentsFragmentDirections.actionAllStudentsFragmentToMainMenuFragment()
            findNavController().navigate(action)
        }

        binding.addNewStudentButton.setOnClickListener {
            val action = AllStudentsFragmentDirections.actionAllStudentsFragmentToStudentFragment(0L)
            findNavController().navigate(action)
        }
    }
}