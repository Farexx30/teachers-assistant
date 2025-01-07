package com.example.teachersassistant.views.fragments.subjectstudentgrade

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.teachersassistant.databinding.FragmentGradeBinding
import com.example.teachersassistant.viewmodels.subjectstudentgrade.GradeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GradeFragment : Fragment() {
    private val args: GradeFragmentArgs by navArgs()

    private lateinit var binding: FragmentGradeBinding

    companion object {
        fun newInstance() = GradeFragment()
    }

    private val viewModel: GradeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (args.gradeId != 0L) {
            viewModel.getGradeById(args.gradeId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGradeBinding.inflate(inflater, container, false)
        binding.gradeViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gradeNumberPicker.apply {
            val grades = arrayOf("2.0", "3.0", "3.5", "4.0", "4.5", "5.0")
            minValue = 0
            maxValue = grades.size - 1
            displayedValues = grades
        }

        binding.saveGradeButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.saveGrade(
                    args.gradeId,
                    args.subjectId,
                    args.studentId
                )

                val action = GradeFragmentDirections.actionGradeFragmentToSubjectStudentInfoFragment(
                    subjectId = args.subjectId,
                    studentId = args.studentId
                )
                findNavController().navigate(action)
            }
        }

        binding.cancelGradeCreationOrUpdateButton.setOnClickListener {
            val action = GradeFragmentDirections.actionGradeFragmentToSubjectStudentInfoFragment(
                subjectId = args.subjectId,
                studentId = args.studentId
            )
            findNavController().navigate(action)
        }

        viewModel.isSaveGradeButtonEnabled.observe(viewLifecycleOwner) { state ->
            binding.saveGradeButton.apply {
                isEnabled = state
                alpha = if (state) 1.0F else 0.5F
            }
        }
    }
}