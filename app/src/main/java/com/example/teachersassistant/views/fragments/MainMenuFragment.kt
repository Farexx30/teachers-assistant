package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.teachersassistant.R
import com.example.teachersassistant.databinding.FragmentLoginBinding
import com.example.teachersassistant.databinding.FragmentMainMenuBinding
import com.example.teachersassistant.viewmodels.CurrentUserSharedViewModel
import com.example.teachersassistant.viewmodels.MainMenuViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint //Not necessary actually but looks like a "good practice" to me
class MainMenuFragment : Fragment() {
    private lateinit var binding: FragmentMainMenuBinding

    companion object {
        fun newInstance() = MainMenuFragment()
    }

    private val viewModel: MainMenuViewModel by viewModels()
    private val sharedViewModel: CurrentUserSharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scheduleButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_scheduleFragment)
        }

        binding.mySubjectsButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_allSubjectsFragment)
        }

        binding.myStudentsButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_allStudentsFragment)
        }

        binding.resetButton.setOnClickListener {
            // TODO
        }

        binding.logoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_initialFragment)
        }

        binding.usernameTextView.text = "Id: ${sharedViewModel.id}, Name: ${sharedViewModel.name}"
    }
}