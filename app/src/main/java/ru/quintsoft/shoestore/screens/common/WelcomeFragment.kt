package ru.quintsoft.shoestore.screens.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import ru.quintsoft.shoestore.R
import ru.quintsoft.shoestore.databinding.WelcomeFragmentBinding

class WelcomeFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: WelcomeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.welcome_fragment, container, false)
        binding.buttonContinue.setOnClickListener { view: View ->
            view.findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment())
        }
        return binding.root
    }
}