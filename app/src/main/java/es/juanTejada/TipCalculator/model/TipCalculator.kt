package es.juanTejada.TipCalculator.model

import java.lang.IllegalArgumentException
import kotlin.math.ceil


class TipCalculator(private val bill: Float, private val percentage:Float, private val dinners:Int) {

    init {
        if(bill < 0 || percentage <0 || dinners < 0){
            throw IllegalArgumentException("Values can not be negative.")
        }
    }

    fun calculateTip():Float  =  percentage/100 * bill

    fun calculateTotal():Float = bill + calculateTip()

    fun calculatePerDinner():Float =  calculateTotal() / dinners

    fun calculatePerDinnerRounded():Float = ceil(calculatePerDinner())

}