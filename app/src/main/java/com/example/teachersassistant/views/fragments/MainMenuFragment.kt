package com.example.teachersassistant.views.fragments

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
import com.example.teachersassistant.R
import com.example.teachersassistant.databinding.FragmentMainMenuBinding
import com.example.teachersassistant.viewmodels.MainMenuViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint //Not necessary actually but looks like a "good practice" to me
class MainMenuFragment : Fragment() {
    private lateinit var binding: FragmentMainMenuBinding

    companion object {
        fun newInstance() = MainMenuFragment()
    }

    private val viewModel: MainMenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
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
        binding.scheduleButton.isEnabled = false
        binding.myStudentsButton.isEnabled = false
        binding.resetButton.isEnabled = false
        binding.logoutButton.isEnabled = false
    }

    private fun enableAllButtons() {
        binding.scheduleButton.isEnabled = true
        binding.myStudentsButton.isEnabled = true
        binding.resetButton.isEnabled = true
        binding.logoutButton.isEnabled = true
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

        // Create and show the dialog
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