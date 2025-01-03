package com.example.teachersassistant.views.fragments

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
import com.example.teachersassistant.adapters.SubjectStudentGradesRecyclerViewAdapter
import com.example.teachersassistant.databinding.FragmentSubjectStudentInfoBinding
import com.example.teachersassistant.viewmodels.SubjectStudentInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
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

        viewModel.getSubjectStudentWithGrades(args.subjectId, args.studentId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        subjectStudentGradesAdapter = SubjectStudentGradesRecyclerViewAdapter(emptyList())

        subjectStudentGradesAdapter.onItemClickListener = { grade ->
            val action = SubjectStudentInfoFragmentDirections.actionSubjectStudentInfoFragmentToGradeFragment(
                subjectId = args.subjectId,
                studentId = args.studentId,
                gradeId = grade.id)
            findNavController().navigate(action)
        }

        lifecycleScope.launch {
            viewModel.grades.collect { grades ->
                subjectStudentGradesAdapter.updateData(grades)
            }
        }

        binding = FragmentSubjectStudentInfoBinding.inflate(inflater, container, false)
        binding.subjectStudentInfoViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.apply {
            subjectStudentGradesRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = subjectStudentGradesAdapter
            }
        }

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
    }
}