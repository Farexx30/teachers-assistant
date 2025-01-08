package com.example.teachersassistant.views.fragments.subject

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachersassistant.R
import com.example.teachersassistant.adapters.recyclerview.AllSubjectsRecyclerViewAdapter
import com.example.teachersassistant.viewmodels.subject.AllSubjectsViewModel
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
        allSubjectsAdapter = AllSubjectsRecyclerViewAdapter(mutableListOf())

        allSubjectsAdapter.onItemClickListener = { subject ->
            val action = AllSubjectsFragmentDirections.actionAllSubjectsFragmentToSubjectInfoFragment(subject.id)
            findNavController().navigate(action)
        }

        allSubjectsAdapter.onItemLongClickListener = { view, subject, position ->
            val popupMenu = PopupMenu(requireContext(), view)

            popupMenu.menuInflater
                .inflate(R.menu.menu_recycler_view_delete_options, popupMenu.menu)
            popupMenu.gravity = Gravity.END

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.delete_option -> {
                        lifecycleScope.launch {
                            viewModel.deleteSubject(subject)
                            allSubjectsAdapter.itemRemoved(position)
                        }
                        true
                    }
                    else -> false
                }
            }

            popupMenu.show()
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.subjects.collect { subjects ->
                    allSubjectsAdapter.fillWithData(subjects.toMutableList())
                }
            }
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