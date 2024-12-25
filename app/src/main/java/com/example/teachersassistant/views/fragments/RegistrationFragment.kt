package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.teachersassistant.R
import com.example.teachersassistant.common.RegistrationOrLoginResult
import com.example.teachersassistant.databinding.FragmentLoginBinding
import com.example.teachersassistant.databinding.FragmentRegistrationBinding
import com.example.teachersassistant.viewmodels.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        binding.registrationViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registrationState.collect { result ->
                    when (result) {
                        RegistrationOrLoginResult.NONE -> {}

                        RegistrationOrLoginResult.FAILED -> Toast.makeText(
                            requireActivity(),
                            "Not good! :(",
                            Toast.LENGTH_LONG
                        ).show()

                        RegistrationOrLoginResult.SUCCESS -> {
                            Toast.makeText(
                                requireActivity(),
                                "Good! :)",
                                Toast.LENGTH_LONG
                            ).show()
                            //Navigate:
                            val action = RegistrationFragmentDirections.actionRegistrationFragmentToMainMenuFragment()
                            findNavController().navigate(action)
                        }
                    }
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goBackToInitialFragmentFromRegistrationButton.setOnClickListener {
            val action = RegistrationFragmentDirections.actionRegistrationFragmentToInitialFragment()
            findNavController().navigate(action)
        }
    }
}