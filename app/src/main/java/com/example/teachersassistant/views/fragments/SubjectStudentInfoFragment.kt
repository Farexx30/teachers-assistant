package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teachersassistant.R
import com.example.teachersassistant.viewmodels.SubjectStudentInfoViewModel

class SubjectStudentInfoFragment : Fragment() {

    companion object {
        fun newInstance() = SubjectStudentInfoFragment()
    }

    private val viewModel: SubjectStudentInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_subject_student_info, container, false)
    }
}