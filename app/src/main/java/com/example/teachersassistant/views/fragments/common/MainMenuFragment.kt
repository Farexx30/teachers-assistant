package com.example.teachersassistant.views.fragments.common

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.teachersassistant.databinding.FragmentMainMenuBinding
import com.example.teachersassistant.viewmodels.common.MainMenuViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainMenuFragment : Fragment() {
    private lateinit var binding: FragmentMainMenuBinding

    companion object {
        fun newInstance() = MainMenuFragment()
    }

    private val viewModel: MainMenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // No ViewModel usage here.
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        binding.mainMenuViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scheduleButton.setOnClickListener {
            val action = MainMenuFragmentDirections.actionMainMenuFragmentToScheduleFragment()
            findNavController().navigate(action)
        }

        binding.mySubjectsButton.setOnClickListener {
            val action = MainMenuFragmentDirections.actionMainMenuFragmentToAllSubjectsFragment()
            findNavController().navigate(action)
        }

        binding.myStudentsButton.setOnClickListener {
            val action = MainMenuFragmentDirections.actionMainMenuFragmentToAllStudentsFragment()
            findNavController().navigate(action)
        }

        binding.resetButton.setOnClickListener {
            showResetAllDataConfirmationDialog()
        }

        binding.logoutButton.setOnClickListener {
            viewModel.logout()

            Toast.makeText(requireContext(), "Goodbye", Toast.LENGTH_SHORT).show()

            val action = MainMenuFragmentDirections.actionMainMenuFragmentToInitialFragment()
            findNavController().navigate(action)
        }
    }

    private fun disableAllButtons() {
        binding.scheduleButton.apply {
            isEnabled = false
            alpha = 0.5F
        }
        binding.myStudentsButton.apply {
            isEnabled = false
            alpha = 0.5F

        }
        binding.resetButton.apply {
            isEnabled = false
            alpha = 0.5F
        }
        binding.logoutButton.apply {
            isEnabled = false
            alpha = 0.5F
        }
    }

    private fun enableAllButtons() {
        binding.scheduleButton.apply {
            isEnabled = true
            alpha = 1.0F
        }
        binding.myStudentsButton.apply {
            isEnabled = true
            alpha = 1.0F
        }
        binding.resetButton.apply {
            isEnabled = true
            alpha = 1.0F
        }
        binding.logoutButton.apply {
            isEnabled = true
            alpha = 1.0F
        }
    }


    private fun showResetAllDataConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Are you sure you want to proceed? This will permanently delete all your data.")
            .setCancelable(false)
            .setPositiveButton("Confirm") { _, _ ->
                lifecycleScope.launch {
                    disableAllButtons()
                    viewModel.resetAllData()
                    showResetAllDataMessageDialog()
                    enableAllButtons()
                }
            }
            .setNegativeButton("Cancel") { _, _ ->
                //No action, just close the dialog.
            }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun showResetAllDataMessageDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Successfully deleted your data!")
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->
                //No action, just close the dialog.
            }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}