package com.example.teachersassistant.views.fragments.subjectstudent

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachersassistant.adapters.recyclerview.AssignStudentsToSubjectRecyclerViewAdapter
import com.example.teachersassistant.viewmodels.subjectstudent.AssignStudentsToSubjectViewModel
import com.example.teachersassistant.databinding.FragmentAssignStudentsToSubjectBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AssignStudentsToSubjectFragment : Fragment() {
    private val args: AssignStudentsToSubjectFragmentArgs by navArgs()
    private lateinit var assignStudentsToSubjectAdapter: AssignStudentsToSubjectRecyclerViewAdapter
    private lateinit var binding: FragmentAssignStudentsToSubjectBinding

    companion object {
        fun newInstance() = AssignStudentsToSubjectFragment()
    }

    private val viewModel: AssignStudentsToSubjectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getNotSubjectStudentsBySubjectId(args.subjectId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        assignStudentsToSubjectAdapter = AssignStudentsToSubjectRecyclerViewAdapter(emptyList())

        assignStudentsToSubjectAdapter.onSelectionChangedListener = { students ->
            viewModel.updateSelectedStudentsCounter(students.size)
        }

        lifecycleScope.launch {
            viewModel.students.collect { students ->
                assignStudentsToSubjectAdapter.fillWithData(students.toList())
            }
        }

        binding = FragmentAssignStudentsToSubjectBinding.inflate(inflater, container, false)
        binding.assignStudentsToSubjectViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.apply {
            studentsToAssignRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = assignStudentsToSubjectAdapter
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.assignSelectedStudentsToSubjectButton.setOnClickListener {
            val studentsToAssign = assignStudentsToSubjectAdapter.getSelectedItems()
            val studentsIdsToAssign = studentsToAssign.map { student ->
                student.id
            }

            lifecycleScope.launch {
                viewModel.assignStudentsToSubject(studentsIdsToAssign, args.subjectId)

                val action = AssignStudentsToSubjectFragmentDirections.actionAssignStudentsToSubjectFragmentToSubjectStudentsFragment(args.subjectId)
                findNavController().navigate(action)
            }
        }

        binding.cancelStudentAssignmentToSubjectButton.setOnClickListener {
            val action = AssignStudentsToSubjectFragmentDirections.actionAssignStudentsToSubjectFragmentToSubjectStudentsFragment(args.subjectId)
            findNavController().navigate(action)
        }

        viewModel.selectedStudentsCounter.observe(viewLifecycleOwner) { counter ->
            binding.assignSelectedStudentsToSubjectButton.apply {
                isEnabled = counter > 0
                alpha = if (counter > 0) 1.0F else 0.5F
            }
        }
    }
}