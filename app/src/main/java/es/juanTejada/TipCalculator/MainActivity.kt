package es.juanTejada.TipCalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import es.juanTejada.TipCalculator.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private val binding: MainActivityBinding by lazy { MainActivityBinding.inflate(layoutInflater) }
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
