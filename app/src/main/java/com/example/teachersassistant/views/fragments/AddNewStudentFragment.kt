package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teachersassistant.R
import com.example.teachersassistant.viewmodels.AddNewStudentViewModel

class AddNewStudentFragment : Fragment() {

    companion object {
        fun newInstance() = AddNewStudentFragment()
    }

    private val viewModel: AddNewStudentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_new_student, container, false)
    }
}