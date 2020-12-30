package ru.quintsoft.shoestore.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.quintsoft.shoestore.screens.listing.Product

class DetailViewModel : ViewModel()  {

    private val _newItem = MutableLiveData<Product>()
    val newItem: LiveData<Product>
        get() = _newItem

    private val _validName = MutableLiveData<Boolean>()
    val validName: LiveData<Boolean>
        get() = _validName

    private val _validSize = MutableLiveData<Boolean>()
    val validSize: LiveData<Boolean>
        get() = _validSize

    private val _validCompany = MutableLiveData<Boolean>()
    val validCompany: LiveData<Boolean>
        get() = _validCompany

    private val _validDescription = MutableLiveData<Boolean>()
    val validDescription: LiveData<Boolean>
        get() = _validDescription

    fun validateAll() {
        _validName.value = true
        _validCompany.value = true
        _validSize.value = true
        _validDescription.value = true
    }

    fun saveData(
        product: Product) {

        val (name, size, company, description) = product

        val nameValid = !name.isBlank()
        _validName.value = nameValid

        val sizeValid = !size.isBlank()
        _validSize.value = sizeValid

        val companyValid = !company.isBlank()
        _validCompany.value = companyValid

        val descriptionValid = !description.isBlank()
        _validDescription.value = descriptionValid

        val dataIsReady = nameValid && sizeValid && companyValid && descriptionValid

        if (dataIsReady) {
            _newItem.value = product
        }
    }
}