package es.juanTejada.TipCalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.juanTejada.TipCalculator.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private val binding: MainActivityBinding by lazy { MainActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
