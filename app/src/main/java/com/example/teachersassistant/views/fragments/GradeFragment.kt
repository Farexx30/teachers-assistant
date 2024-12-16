package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.teachersassistant.R
import com.example.teachersassistant.databinding.FragmentGradeBinding
import com.example.teachersassistant.databinding.FragmentSubjectStudentInfoBinding
import com.example.teachersassistant.viewmodels.GradeViewModel

class GradeFragment : Fragment() {
    private lateinit var binding: FragmentGradeBinding

    companion object {
        fun newInstance() = GradeFragment()
    }

    private val viewModel: GradeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGradeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveGradeButton.setOnClickListener {
            //TODO: Insert/Update grade
            findNavController().navigate(R.id.action_gradeFragment_to_subjectStudentInfoFragment)
        }

        binding.cancelGradeCreationOrUpdateButton.setOnClickListener {
            findNavController().navigate(R.id.action_gradeFragment_to_subjectStudentInfoFragment)
        }
    }
}