package es.juanTejada.TipCalculator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TipViewModel : ViewModel() {
    private val _bill: MutableLiveData<Float> = MutableLiveData(0.00f)
    private val bill: LiveData<Float>
    get() = _bill
    //TODO
}