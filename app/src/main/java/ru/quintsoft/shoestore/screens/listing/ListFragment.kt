package ru.quintsoft.shoestore.screens.listing

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import ru.quintsoft.shoestore.R
import ru.quintsoft.shoestore.databinding.ListFragmentBinding
import ru.quintsoft.shoestore.databinding.ProductItemBinding
import ru.quintsoft.shoestore.screens.detail.DetailFragmentDirections.Companion.actionGlobalLoginFragment

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var binding: ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.list_fragment,
            container,
            false
        )
        binding.fab.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                ListFragmentDirections.actionListFragmentToDetailFragment()
            )
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = activityViewModels<ListViewModel>().value
        viewModel.itemList.observe(viewLifecycleOwner) {
            with(binding) {
                layoutContainerItem.removeAllViews() // Remove all item_widgets
                for (item in it) {
                    val productItemBinding = DataBindingUtil.inflate<ProductItemBinding>(
                        layoutInflater,
                        R.layout.product_item,
                        layoutContainerItem,
                        true
                    )
                    productItemBinding.productItem = item
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.navdrawer_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_logout -> {
                findNavController().navigate(actionGlobalLoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}