package com.example.teachersassistant.views.fragments

import android.annotation.SuppressLint
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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachersassistant.R
import com.example.teachersassistant.adapters.SubjectDatesRecyclerViewAdapter
import com.example.teachersassistant.databinding.FragmentSubjectInfoBinding
import com.example.teachersassistant.viewmodels.SubjectInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SubjectInfoFragment : Fragment() {
    private val args: SubjectInfoFragmentArgs by navArgs()
    private var subjectId = 0L

    private lateinit var subjectDatesAdapter: SubjectDatesRecyclerViewAdapter
    private lateinit var binding: FragmentSubjectInfoBinding

    companion object {
        fun newInstance() = SubjectInfoFragment()
    }

    private val viewModel: SubjectInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subjectId = args.subjectId

        if (subjectId != 0L) {
            viewModel.getSubjectData(subjectId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        subjectDatesAdapter = SubjectDatesRecyclerViewAdapter(mutableListOf())

        subjectDatesAdapter.onItemClickListener = { date ->
            val action = SubjectInfoFragmentDirections.actionSubjectInfoFragmentToSubjectDateFragment(
                date.id,
                subjectId
            )
            findNavController().navigate(action)
        }

        subjectDatesAdapter.onItemLongClickListener = { view, subjectDate, position ->
            val popupMenu = PopupMenu(requireContext(), view)

            popupMenu.menuInflater
                .inflate(R.menu.menu_recycler_view_delete_options, popupMenu.menu)
            popupMenu.gravity = Gravity.END

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.delete_option -> {
                        lifecycleScope.launch {
                            viewModel.deleteSubjectDate(subjectDate)
                            subjectDatesAdapter.itemRemoved(position)
                        }
                        true
                    }
                    else -> false
                }
            }

            popupMenu.show()
        }

        lifecycleScope.launch {
            viewModel.subjectDates.collect { subjectDates ->
                subjectDatesAdapter.fillWithData(subjectDates.toMutableList())
            }
        }

        binding = FragmentSubjectInfoBinding.inflate(inflater, container, false)
        binding.subjectInfoViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.apply {
            subjectDatesRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = subjectDatesAdapter
            }
        }

        if (subjectId != 0L) {
            adjustUIWhenSubjectExist()
        }
        else {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.subjectId.collect { id ->
                        subjectId = id
                    }
                }
            }

            adjustUIWhenSubjectNotExist()
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNewSubjectDateButton.setOnClickListener {
            val action = SubjectInfoFragmentDirections.actionSubjectInfoFragmentToSubjectDateFragment(
                subjectId = subjectId,
                dateId = 0L)
            findNavController().navigate(action)
        }

        binding.goToAssignedStudentsButton.setOnClickListener {
            val action = SubjectInfoFragmentDirections.actionSubjectInfoFragmentToSubjectStudentsFragment(subjectId)
            findNavController().navigate(action)
        }

        binding.cancelSubjectCreationButton.setOnClickListener {
            val action = SubjectInfoFragmentDirections.actionSubjectInfoFragmentToAllSubjectsFragment()
            findNavController().navigate(action)
        }

        binding.saveNewSubjectButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.saveNewSubject()
                viewModel.getSubjectData(subjectId)

                adjustUIWhenSubjectExist()
            }
        }

        binding.goToScheduleFromSubjectInfoButton.setOnClickListener {
            val action = SubjectInfoFragmentDirections.actionSubjectInfoFragmentToScheduleFragment()
            findNavController().navigate(action)
        }

        binding.goToAllSubjectsFromSubjectInfoButton.setOnClickListener {
            val action = SubjectInfoFragmentDirections.actionSubjectInfoFragmentToAllSubjectsFragment()
            findNavController().navigate(action)
        }


        binding.editSubjectNameButton.setOnClickListener {
            if (!binding.subjectNameEditText.isEnabled) {
                binding.editSubjectNameButton.text = "Save"
                binding.subjectNameEditText.isEnabled = true
            }
            else {
                lifecycleScope.launch {
                    viewModel.updateSubjectName(subjectId)

                    binding.editSubjectNameButton.text = "Edit"
                    binding.subjectNameEditText.isEnabled = false
                }
            }
        }
    }

    private fun adjustUIWhenSubjectNotExist() {
        binding.navButtonsLinearLayout.visibility = View.GONE
        binding.editSubjectNameButton.visibility = View.GONE
        binding.creationButtonsLinearLayout.visibility = View.VISIBLE
        binding.subjectNameEditText.isEnabled = true
    }

    private fun adjustUIWhenSubjectExist() {
        binding.creationButtonsLinearLayout.visibility = View.GONE
        binding.navButtonsLinearLayout.visibility = View.VISIBLE
        binding.editSubjectNameButton.visibility = View.VISIBLE
        binding.editSubjectNameButton.isEnabled = true
        binding.subjectNameEditText.isEnabled = false
    }
}