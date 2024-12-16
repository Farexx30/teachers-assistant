package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.teachersassistant.R
import com.example.teachersassistant.databinding.FragmentAllStudentsBinding
import com.example.teachersassistant.databinding.FragmentMainMenuBinding
import com.example.teachersassistant.viewmodels.AllStudentsViewModel

class AllStudentsFragment : Fragment() {
    private lateinit var binding: FragmentAllStudentsBinding

    companion object {
        fun newInstance() = AllStudentsFragment()
    }

    private val viewModel: AllStudentsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllStudentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goToMainMenuFromAllStudentsButton.setOnClickListener {
            findNavController().navigate(R.id.action_allStudentsFragment_to_mainMenuFragment)
        }

        binding.addNewStudentButton.setOnClickListener {
            findNavController().navigate(R.id.action_allStudentsFragment_to_studentFragment)
        }

        // TODO: Binding to EXISTING student
    }
}