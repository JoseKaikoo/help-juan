package es.juanTejada.TipCalculator.ui.main.records

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.juanTejada.TipCalculator.model.TipRepository

@Suppress("UNCHECKED_CAST")
class TipRecordsViewModelFactory(private val tipRepository: TipRepository): ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T  = TipRecordsViewModel(tipRepository) as T
}
