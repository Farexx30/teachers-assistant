package com.example.teachersassistant.views.fragments.common

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.teachersassistant.databinding.FragmentInitialBinding
import com.example.teachersassistant.viewmodels.common.InitialViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitialFragment : Fragment() {
    private lateinit var binding: FragmentInitialBinding

    companion object {
        fun newInstance() = InitialFragment()
    }

    private val viewModel: InitialViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInitialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val action = InitialFragmentDirections.actionInitialFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        binding.registrationButton.setOnClickListener {
            val action = InitialFragmentDirections.actionInitialFragmentToRegistrationFragment()
            findNavController().navigate(action)
        }
    }
}