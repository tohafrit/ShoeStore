package ru.quintsoft.shoestore.screens.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import ru.quintsoft.shoestore.R
import ru.quintsoft.shoestore.databinding.InstructionFragmentBinding

class InstructionFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: InstructionFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.instruction_fragment, container, false)
        binding.buttonList.setOnClickListener { view: View ->
            view.findNavController().navigate(InstructionFragmentDirections.actionInstructionFragmentToListFragment())
        }
        return binding.root
    }
}