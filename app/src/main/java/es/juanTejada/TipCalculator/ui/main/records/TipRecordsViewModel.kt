package es.juanTejada.TipCalculator.ui.main.records

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.juanTejada.TipCalculator.model.TipCalculator
import es.juanTejada.TipCalculator.model.TipRepository

class TipRecordsViewModel(tipRepository: TipRepository) : ViewModel() {

    val bills:LiveData<List<TipCalculator>> = tipRepository.getBills()
}