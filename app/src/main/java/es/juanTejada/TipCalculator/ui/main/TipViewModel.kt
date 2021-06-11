package es.juanTejada.TipCalculator.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.juanTejada.TipCalculator.model.TipCalculator
import kotlin.math.ceil

class TipViewModel : ViewModel() {
    private val _bill: MutableLiveData<Float> = MutableLiveData(0.00f)
    val bill: LiveData<Float>
        get() = _bill

    private val _total: MutableLiveData<Float> = MutableLiveData(0.00f)
    val total: LiveData<Float>
        get() = _total

    private val _percentage: MutableLiveData<Float> = MutableLiveData(0.00f)
    val percentage: MutableLiveData<Float>
        get() = _percentage

    private val _diners: MutableLiveData<Int> = MutableLiveData(0)
    val diners: MutableLiveData<Int>
        get() = _diners

    private val _dinersRounded: MutableLiveData<Float> = MutableLiveData(0.00f)
    val dinersRounded: MutableLiveData<Float>
        get() = _dinersRounded

    private val _dinerAmount: MutableLiveData<Float> = MutableLiveData(0.00f)
    val dinnerAmount: MutableLiveData<Float>
        get() = _dinerAmount


    fun setBill(bill: Float) {
        _bill.value = bill
        _total.value = calcTotal(bill)
    }

    fun setPercentage(percentage: Float) {
        Log.d("pollo",percentage.toString())
        _percentage.value = calcPercentage(percentage)
    }

    fun setDiners(diners: Int) {
        _diners.value = diners
        _dinerAmount.value = calcPerDinner(diners)
        _dinersRounded.value = calcDinnerRounded(diners)
    }

    fun resetBill() {
        _total.value = 0.00f
        _percentage.value = 0.00f
        _bill.value = 0.00f
    }

    fun resetDiner() {
        _diners.value = 0
        _dinerAmount.value = 0.00f
    }

    fun saveBill() {
        val bill = TipCalculator(0, _bill.value!!, _percentage.value!!, _diners.value!!)
        //TODO SAVE ON ROOM
    }


    private fun calcPercentage(percentage: Float): Float = percentage / 100 * bill.value!!

    private fun calcTotal(bill: Float): Float = bill + _percentage.value!!

    private fun calcPerDinner(diners: Int): Float = calcTotal(bill.value!!) / diners

    private fun calcDinnerRounded(diners: Int): Float =
        ceil(calcPerDinner(diners))
}