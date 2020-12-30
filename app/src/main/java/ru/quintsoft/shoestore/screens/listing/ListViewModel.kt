package ru.quintsoft.shoestore.screens.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel()  {

    private val _itemList = MutableLiveData<List<Product>>()
    val itemList: LiveData<List<Product>>
        get() = _itemList

    fun addItem(item: Product) {
        val list = _itemList.value?.toMutableList() ?: mutableListOf()
        list.add(item)
        _itemList.value = list
    }

    init {
        _itemList.value = mutableListOf(
            Product("Shoe 1","7.5","Garde","Move from the gym to the street in the Men's adidas Questar Flow Next Sneaker."),
            Product("Shoe 2","8.0","Airline","Move from the gym to the street in the Men's adidas Questar Flow Next Sneaker."),
            Product("Shoe 3","8.5","Sealmark","Move from the gym to the street in the Men's adidas Questar Flow Next Sneaker."),
            Product("Shoe 4","9.0","WoodGoods","Move from the gym to the street in the Men's adidas Questar Flow Next Sneaker."),
            Product("Shoe 5","10.0","Sealmark","Move from the gym to the street in the Men's adidas Questar Flow Next Sneaker."),
            Product("Shoe 6","11.0","Airline","Move from the gym to the street in the Men's adidas Questar Flow Next Sneaker.")
        )
    }
}