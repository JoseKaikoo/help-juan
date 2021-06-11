package es.juanTejada.TipCalculator.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.juanTejada.TipCalculator.model.TipRepository

@Suppress("UNCHECKED_CAST")
class TipViewModelFactory(private val repository: TipRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = TipViewModel(repository) as T

}