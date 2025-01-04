package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachersassistant.R
import com.example.teachersassistant.adapters.StudentsRecyclerViewAdapter
import com.example.teachersassistant.databinding.FragmentAllStudentsBinding
import com.example.teachersassistant.viewmodels.AllStudentsViewModel
import dagger.hilt.android.AndroidEntryPoint
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
        studentsAdapter = StudentsRecyclerViewAdapter(mutableListOf())

        studentsAdapter.onItemClickListener = { student ->
            val action = AllStudentsFragmentDirections.actionAllStudentsFragmentToStudentFragment(student.id)
            findNavController().navigate(action)
        }

        studentsAdapter.onItemLongClickListener = { view, student, position ->
            val popupMenu = PopupMenu(requireContext(), view)

            popupMenu.menuInflater
                .inflate(R.menu.menu_recycler_view_delete_options, popupMenu.menu)
            popupMenu.gravity = Gravity.END

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.delete_option -> {
                        lifecycleScope.launch {
                            viewModel.deleteStudent(student)
                            studentsAdapter.itemRemoved(position)
                        }
                        true
                    }
                    else -> false
                }
            }

            popupMenu.show()
        }

        lifecycleScope.launch {
            viewModel.students.collect { students ->
                studentsAdapter.fillWithData(students.toMutableList())
            }
        }

        binding = FragmentAllStudentsBinding.inflate(inflater, container, false)
        binding.allStudentsViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.apply {
            allStudentsRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = studentsAdapter
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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