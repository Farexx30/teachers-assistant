package com.example.teachersassistant.views.fragments.subjectstudent

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
import com.example.teachersassistant.adapters.recyclerview.SubjectStudentGradesRecyclerViewAdapter
import com.example.teachersassistant.databinding.FragmentSubjectStudentInfoBinding
import com.example.teachersassistant.viewmodels.subjectstudent.SubjectStudentInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SubjectStudentInfoFragment : Fragment() {
    private val args: SubjectStudentInfoFragmentArgs by navArgs()

    private lateinit var subjectStudentGradesAdapter: SubjectStudentGradesRecyclerViewAdapter
    private lateinit var binding: FragmentSubjectStudentInfoBinding

    companion object {
        fun newInstance() = SubjectStudentInfoFragment()
    }

    private val viewModel: SubjectStudentInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getSubjectStudentWithGrades(args.subjectId, args.studentId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        subjectStudentGradesAdapter = SubjectStudentGradesRecyclerViewAdapter(mutableListOf())

        subjectStudentGradesAdapter.onItemClickListener = { grade ->
            val action = SubjectStudentInfoFragmentDirections.actionSubjectStudentInfoFragmentToGradeFragment(
                subjectId = args.subjectId,
                studentId = args.studentId,
                gradeId = grade.id)
            findNavController().navigate(action)
        }

        subjectStudentGradesAdapter.onItemLongClickListener = { view, grade, position ->
            val popupMenu = PopupMenu(requireContext(), view)

            popupMenu.menuInflater
                .inflate(R.menu.menu_recycler_view_delete_options, popupMenu.menu)
            popupMenu.gravity = Gravity.END

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.delete_option -> {
                        lifecycleScope.launch {
                            viewModel.deleteGrade(grade)
                            subjectStudentGradesAdapter.itemRemoved(position)
                            viewModel.calculateAndUpdateAverageGrade()
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
                viewModel.grades.collect { grades ->
                    subjectStudentGradesAdapter.fillWithData(grades.toMutableList())
                }
            }
        }

        binding = FragmentSubjectStudentInfoBinding.inflate(inflater, container, false)
        binding.subjectStudentInfoViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.apply {
            subjectStudentGradesRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = subjectStudentGradesAdapter
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goBackToSubjectStudentsFromSubjectStudentInfoButton.setOnClickListener {
            val action = SubjectStudentInfoFragmentDirections.actionSubjectStudentInfoFragmentToSubjectStudentsFragment(args.subjectId)
            findNavController().navigate(action)
        }

        binding.newGradeButton.setOnClickListener {
            val action = SubjectStudentInfoFragmentDirections.actionSubjectStudentInfoFragmentToGradeFragment(
                subjectId = args.subjectId,
                studentId = args.studentId,
                gradeId = 0L)
            findNavController().navigate(action)
        }
    }
}