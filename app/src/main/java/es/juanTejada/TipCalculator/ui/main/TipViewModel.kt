package es.juanTejada.TipCalculator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.juanTejada.TipCalculator.model.TipCalculator

class TipViewModel : ViewModel() {
    private val _bill: MutableLiveData<Float> = MutableLiveData(0.00f)
    val bill: LiveData<Float>
    get() = _bill

    private val _percentage: MutableLiveData<Float> = MutableLiveData(10f)
    val percentage: MutableLiveData<Float>
    get() = _percentage

    private val _diners: MutableLiveData<Int> = MutableLiveData(0)
    val diners: MutableLiveData<Int>
        get() = _diners



    fun setBill(bill: Float) {
        _bill.value = bill
    }
    fun setPercentage(percentage: Float){
        _percentage.value = percentage
    }

    fun setDiners(diners: Int){
        _diners.value = diners
    }

    fun resetBill() {
        _bill.value = 0.00f
        _percentage.value = 0.00f
    }

    fun resetDiner() {
        _diners.value = 0
    }

    fun saveBill() {
        val bill = TipCalculator(0,_bill.value!!, _percentage.value!!, _diners.value!!)
        //TODO SAVE ON ROOM
    }
}