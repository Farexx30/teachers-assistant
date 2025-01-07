package com.example.teachersassistant.views.fragments.subject

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
import com.example.teachersassistant.databinding.FragmentSubjectDateBinding
import com.example.teachersassistant.viewmodels.subject.SubjectDateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class SubjectDateFragment : Fragment() {
    private val args: SubjectDateFragmentArgs by navArgs()
    private lateinit var binding: FragmentSubjectDateBinding

    companion object {
        fun newInstance() = SubjectDateFragment()
    }

    private val viewModel: SubjectDateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (args.dateId != 0L) {
            viewModel.getSubjectDate(args.dateId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubjectDateBinding.inflate(inflater, container, false)
        binding.subjectDateViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.subjectDayNumberPicker.apply {
            val dayOptions = resources.getStringArray(R.array.day_options)
            minValue = 0
            maxValue = dayOptions.size - 1
            displayedValues = dayOptions
        }

        binding.subjectStartHourNumberPicker.apply {
            minValue = 0
            maxValue = 23
            setFormatter { value -> String.format(Locale.US, "%02d", value) }
        }

        binding.subjectStartMinuteNumberPicker.apply {
            minValue = 0
            maxValue = 59
            setFormatter { value -> String.format(Locale.US, "%02d", value) }
        }

        binding.subjectEndHourNumberPicker.apply {
            minValue = 0
            maxValue = 23
            setFormatter { value -> String.format(Locale.US, "%02d", value) }
        }

        binding.subjectEndMinuteNumberPicker.apply {
            minValue = 0
            maxValue = 59
            setFormatter { value -> String.format(Locale.US, "%02d", value) }
        }

        binding.saveSubjectDateButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.saveSubjectDate(args.subjectId, args.dateId)
                val action =
                    SubjectDateFragmentDirections.actionSubjectDateFragmentToSubjectInfoFragment(
                        subjectId = args.subjectId
                    )
                findNavController().navigate(action)
            }
        }

        binding.cancelSubjectDateButton.setOnClickListener {
            val action =
                SubjectDateFragmentDirections.actionSubjectDateFragmentToSubjectInfoFragment(
                    subjectId = args.subjectId
                )
            findNavController().navigate(action)
        }
    }
}
