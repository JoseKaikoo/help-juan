package es.juanTejada.TipCalculator.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.IllegalArgumentException
import java.util.*
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
    val dinners:Int,
    @ColumnInfo(name = "date")
    var date: String = Date().toString(),
    @ColumnInfo(name = "name")
    val name: String
    ) {

    init {
        if(bill < 0 || percentage <0 || dinners < 0){
            throw IllegalArgumentException("Values can not be negative.")
        }
    }
}