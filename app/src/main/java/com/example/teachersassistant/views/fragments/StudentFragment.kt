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
import com.example.teachersassistant.R
import com.example.teachersassistant.databinding.FragmentStudentBinding
import com.example.teachersassistant.databinding.FragmentSubjectInfoBinding
import com.example.teachersassistant.viewmodels.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StudentFragment : Fragment() {
    private val args: StudentFragmentArgs by navArgs()

    private lateinit var binding: FragmentStudentBinding

    companion object {
        fun newInstance() = StudentFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (args.studentId != 0L) {
            viewModel.getStudentData(args.studentId)
        }
    }

    private val viewModel: StudentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentBinding.inflate(inflater, container, false)
        binding.studentViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveStudentButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.saveStudent(args.studentId)

                val action = StudentFragmentDirections.actionStudentFragmentToAllStudentsFragment()
                findNavController().navigate(action)
            }
        }

        binding.cancelStudentButton.setOnClickListener {
            val action = StudentFragmentDirections.actionStudentFragmentToAllStudentsFragment()
            findNavController().navigate(action)
        }
    }
}