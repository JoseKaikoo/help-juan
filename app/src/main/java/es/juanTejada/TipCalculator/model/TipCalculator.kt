package es.juanTejada.TipCalculator.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.IllegalArgumentException
import kotlin.math.ceil

@Entity
data class TipCalculator(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", index = true)
    val id: Long = 0,
    @ColumnInfo(name = "bill")
    val bill: Float,
    @ColumnInfo(name = "percentage")
    val percentage:Float,
    @ColumnInfo(name = "dinners")
    val dinners:Int
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