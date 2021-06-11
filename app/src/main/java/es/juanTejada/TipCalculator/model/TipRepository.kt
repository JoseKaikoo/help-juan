package es.juanTejada.TipCalculator.model

import androidx.lifecycle.LiveData

class TipRepository(val tipDao: TipDao)  {
    
    
    fun saveBill(bill: TipCalculator) = tipDao.saveBill(bill)


    fun getBills(): LiveData<List<TipCalculator>> = tipDao.getBills()
}