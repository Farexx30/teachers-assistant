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
import com.example.teachersassistant.databinding.FragmentInitialBinding
import com.example.teachersassistant.databinding.FragmentLoginBinding
import com.example.teachersassistant.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginState.collect { result ->
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
                            val action = LoginFragmentDirections.actionLoginFragmentToMainMenuFragment()
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

        binding.goBackToInitialFragmentFromLoginButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToInitialFragment()
            findNavController().navigate(action)
        }
    }
}