package com.example.teachersassistant.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.teachersassistant.R
import com.example.teachersassistant.databinding.FragmentInitialBinding
import com.example.teachersassistant.databinding.FragmentLoginBinding
import com.example.teachersassistant.viewmodels.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goBackToInitialFragmentFromLoginButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_initialFragment)
        }
    }
}