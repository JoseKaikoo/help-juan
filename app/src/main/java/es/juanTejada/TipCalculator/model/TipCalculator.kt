package es.juanTejada.TipCalculator.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.IllegalArgumentException
import kotlin.math.ceil

@Entity
class TipCalculator(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", index = true)
    val id: Long = 0,
    private val bill: Float,
    private val percentage:Float,
    private val dinners:Int
    ) {

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