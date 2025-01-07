package com.example.teachersassistant.views.fragments.subjectstudent

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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachersassistant.R
import com.example.teachersassistant.adapters.recyclerview.StudentsRecyclerViewAdapter
import com.example.teachersassistant.databinding.FragmentSubjectStudentsBinding
import com.example.teachersassistant.viewmodels.subjectstudent.SubjectStudentsViewModel
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

        studentsAdapter.onItemLongClickListener = { view, student, position ->
            val popupMenu = PopupMenu(requireContext(), view)

            popupMenu.menuInflater
                .inflate(R.menu.menu_recycler_view_remove_options, popupMenu.menu)
            popupMenu.gravity = Gravity.END

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.remove_option -> {
                        lifecycleScope.launch {
                            viewModel.removeStudentFromSubject(student, args.subjectId)
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

        binding = FragmentSubjectStudentsBinding.inflate(inflater, container, false)
        binding.subjectStudentsViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.apply {
            subjectStudentsRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = studentsAdapter
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