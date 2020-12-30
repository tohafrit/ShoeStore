package ru.quintsoft.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.quintsoft.shoestore.R
import ru.quintsoft.shoestore.databinding.LoginFragmentBinding

class LoginFragment: Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.hasValidEmail.observe(viewLifecycleOwner, Observer { valid ->
            binding.labelEmail.error = if (valid) {
                null
            } else {
                getString(R.string.error_email_empty)
            }
        })

        viewModel.hasValidPassword.observe(viewLifecycleOwner, Observer { valid ->
            binding.labelPassword.error = if (valid) {
                null
            } else {
                getString(R.string.error_password_empty)
            }
        })

        viewModel.hasInfo.observe(viewLifecycleOwner, Observer { allowed ->
            if (allowed) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        })

        with(binding) {
            editEmail.addTextChangedListener {
                viewModel.testEmailValid(binding.editEmail.text?.toString() ?: "")
            }
            editPassword.addTextChangedListener {
                viewModel.testPasswordValid(binding.editPassword.text?.toString() ?: "")
            }
            signIn.setOnClickListener {
                viewModel.checkPass()
            }
            registerNow.setOnClickListener {
                viewModel.checkPass()
            }
        }
    }
}