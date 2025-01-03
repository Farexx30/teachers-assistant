package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.teachersassistant.R
import com.example.teachersassistant.databinding.FragmentGradeBinding
import com.example.teachersassistant.databinding.FragmentSubjectStudentInfoBinding
import com.example.teachersassistant.viewmodels.GradeViewModel
import dagger.hilt.android.AndroidEntryPoint

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

        binding.saveGradeButton.setOnClickListener {
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

        binding.cancelGradeCreationOrUpdateButton.setOnClickListener {
            val action = GradeFragmentDirections.actionGradeFragmentToSubjectStudentInfoFragment(
                subjectId = args.subjectId,
                studentId = args.studentId
            )
            findNavController().navigate(action)
        }
    }
}