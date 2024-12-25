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
import com.example.teachersassistant.adapters.AssignStudentsToSubjectRecyclerViewAdapter
import com.example.teachersassistant.viewmodels.AssignStudentsToSubjectViewModel
import com.example.teachersassistant.databinding.FragmentAssignStudentsToSubjectBinding
import com.example.teachersassistant.dtos.student.StudentDto

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

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        assignStudentsToSubjectAdapter = AssignStudentsToSubjectRecyclerViewAdapter(emptyList())

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
            val action = AssignStudentsToSubjectFragmentDirections.actionAssignStudentsToSubjectFragmentToSubjectStudentsFragment(args.subjectId)
            findNavController().navigate(action)
        }

        binding.cancelStudentAssignmentToSubjectButton.setOnClickListener {
            val action = AssignStudentsToSubjectFragmentDirections.actionAssignStudentsToSubjectFragmentToSubjectStudentsFragment(args.subjectId)
            findNavController().navigate(action)
        }

        //TODO: Students selection
    }
}