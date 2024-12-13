package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teachersassistant.viewmodels.AllSubjectsViewModel
import com.example.teachersassistant.R

class AllSubjectsFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_all_subjects, container, false)
    }
}