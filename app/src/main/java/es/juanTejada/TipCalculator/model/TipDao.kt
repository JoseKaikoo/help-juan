package es.juanTejada.TipCalculator.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TipDao {

    @Insert
    fun saveBill(bill: TipCalculator)

    @Query("Select * from TipCalculator")
    fun getBills(): LiveData<List<TipCalculator>>
}