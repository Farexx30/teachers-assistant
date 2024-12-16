package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.teachersassistant.viewmodels.AllSubjectsViewModel
import com.example.teachersassistant.R
import com.example.teachersassistant.databinding.FragmentAllStudentsBinding
import com.example.teachersassistant.databinding.FragmentAllSubjectsBinding

class AllSubjectsFragment : Fragment() {
    private lateinit var binding: FragmentAllSubjectsBinding

    companion object {
        fun newInstance() = AllSubjectsFragment()
    }

    private val viewModel: AllSubjectsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllSubjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goToMainMenuFromAllSubjectsButton.setOnClickListener {
            findNavController().navigate(R.id.action_allSubjectsFragment_to_mainMenuFragment)
        }

        binding.addNewSubjectButton.setOnClickListener {
            findNavController().navigate(R.id.action_allSubjectsFragment_to_subjectInfoFragment)
        }

        //TODO: Binding to EXISTING subject
    }
}