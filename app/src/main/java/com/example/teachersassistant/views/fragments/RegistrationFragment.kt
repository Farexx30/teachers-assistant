package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.teachersassistant.R
import com.example.teachersassistant.databinding.FragmentLoginBinding
import com.example.teachersassistant.databinding.FragmentRegistrationBinding
import com.example.teachersassistant.viewmodels.RegistrationViewModel

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goBackToInitialFragmentFromRegistrationButton.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_initialFragment)
        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_mainMenuFragment)
        }
    }
}