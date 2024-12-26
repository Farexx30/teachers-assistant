package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachersassistant.adapters.AllSubjectsRecyclerViewAdapter
import com.example.teachersassistant.viewmodels.AllSubjectsViewModel
import com.example.teachersassistant.databinding.FragmentAllSubjectsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllSubjectsFragment : Fragment() {
    private lateinit var allSubjectsAdapter: AllSubjectsRecyclerViewAdapter
    private lateinit var binding: FragmentAllSubjectsBinding

    companion object {
        fun newInstance() = AllSubjectsFragment()
    }

    private val viewModel: AllSubjectsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        allSubjectsAdapter = AllSubjectsRecyclerViewAdapter(emptyList())

        allSubjectsAdapter.onItemClickListener = { subject ->
            val action = AllSubjectsFragmentDirections.actionAllSubjectsFragmentToSubjectInfoFragment(subject.id)
            findNavController().navigate(action)
        }

        binding = FragmentAllSubjectsBinding.inflate(inflater, container, false)
        binding.apply {
            allSubjectsRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = allSubjectsAdapter
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.subjects.collect { subjects ->
                allSubjectsAdapter.updateData(subjects)
            }
        }

        binding.goToMainMenuFromAllSubjectsButton.setOnClickListener {
            val action = AllSubjectsFragmentDirections.actionAllSubjectsFragmentToMainMenuFragment()
            findNavController().navigate(action)
        }

        binding.addNewSubjectButton.setOnClickListener {
            val action = AllSubjectsFragmentDirections.actionAllSubjectsFragmentToSubjectInfoFragment(0L)
            findNavController().navigate(action)
        }
    }
}