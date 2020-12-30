package ru.quintsoft.shoestore.screens.detail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import ru.quintsoft.shoestore.R
import ru.quintsoft.shoestore.databinding.DetailFragmentBinding
import ru.quintsoft.shoestore.screens.listing.ListViewModel
import ru.quintsoft.shoestore.screens.listing.Product

class DetailFragment: Fragment() {

    private lateinit var binding: DetailFragmentBinding
    private lateinit var listViewModel: ListViewModel
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var navController: NavController

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            detailViewModel.validateAll()
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.detail_fragment,
            container,
            false
        )
        navController = findNavController()
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        detailViewModel.newItem.observe(viewLifecycleOwner) {
            listViewModel.addItem(it)
            navController.navigateUp()
        }

        with(binding) {
            editName.addTextChangedListener(textWatcher)
            editSize.addTextChangedListener(textWatcher)
            editCompany.addTextChangedListener(textWatcher)
            editDescription.addTextChangedListener(textWatcher)

            detailViewModel.validName.observe(viewLifecycleOwner) {
                textName.error = if (!it) {
                    getString(R.string.error_name_empty)
                } else {
                    null
                }
            }
            detailViewModel.validSize.observe(viewLifecycleOwner) {
                textSize.error = if (!it) {
                    getString(R.string.error_size_empty)
                } else {
                    null
                }
            }
            detailViewModel.validCompany.observe(viewLifecycleOwner) {
                textCompany.error = if (!it) {
                    getString(R.string.error_company_empty)
                } else {
                    null
                }
            }
            detailViewModel.validDescription.observe(viewLifecycleOwner) {
                textDescription.error = if (!it) {
                    getString(R.string.error_description_empty)
                } else {
                    null
                }
            }

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel = activityViewModels<ListViewModel>().value
        binding.viewModel = detailViewModel
        binding.product = Product("","","","")
        binding.buttonCancel.setOnClickListener {
            navController.navigateUp()
        }
    }
}