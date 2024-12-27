package com.example.teachersassistant.views.fragments

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import com.example.teachersassistant.adapters.SubjectDatesRecyclerViewAdapter
import com.example.teachersassistant.common.Day
import com.example.teachersassistant.common.RegistrationOrLoginResult
import com.example.teachersassistant.databinding.FragmentSubjectInfoBinding
import com.example.teachersassistant.dtos.subject.SubjectWithDatesDto
import com.example.teachersassistant.viewmodels.SubjectInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalTime

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
        subjectDatesAdapter = SubjectDatesRecyclerViewAdapter(emptyList())

        subjectDatesAdapter.onItemClickListener = { date ->
            val action = SubjectInfoFragmentDirections.actionSubjectInfoFragmentToSubjectDateFragment(
                date.id,
                subjectId)
            findNavController().navigate(action)
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

        lifecycleScope.launch {
            viewModel.subjectDates.collect { subjectDates ->
                subjectDatesAdapter.updateData(subjectDates)
            }
        }

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
            viewModel.saveNewSubject()

            adjustUIWhenSubjectExist()
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
                viewModel.updateSubjectName(subjectId)

                binding.editSubjectNameButton.text = "Edit"
                binding.subjectNameEditText.isEnabled = false
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