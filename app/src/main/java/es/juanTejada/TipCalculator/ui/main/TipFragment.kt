package es.juanTejada.TipCalculator.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import es.juanTejada.TipCalculator.R
import es.juanTejada.TipCalculator.databinding.TipFragmentBinding
import es.juanTejada.TipCalculator.model.TipCalculator
import es.juanTejada.TipCalculator.model.isNotEmpty

class TipFragment : Fragment(R.layout.tip_fragment) {

    private lateinit var tipCalculator: TipCalculator
    private val binding: TipFragmentBinding by lazy {
        TipFragmentBinding.bind(requireView())
    }

    private var bill = 0.00f
    private var percentage = 0.00f
    private var diners = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onStart() {
        super.onStart()
        setTextWatchers()
    }

    // set the TextWatchers for focusable Edit Texts.
    private fun setTextWatchers() {
        binding.edTextBill.doAfterTextChanged { calculateBill() }
        binding.edTextTipPercentage.doAfterTextChanged { calculatePercentage() }
        binding.edTextDiner.doAfterTextChanged { calculateDiners() }
    }

    private fun setupViews() {
        binding.btnResetBill.setOnClickListener { resetBill() }
        binding.btnResetDiners.setOnClickListener { resetDiner() }
        binding.edTextBill.requestFocus()
    }

    /*
        1. Capture values from editText
        2. Calculate values calculates
        3. Set Text from values calculates
     */
    private fun calculateBill() {
        captureValues()
        createTipCalculator()
        setText()
    }

    private fun calculatePercentage() {
        captureValues()
        createTipCalculator()
        setText()
    }

    private fun calculateDiners() {
        diners = captureValue(binding.edTextDiner, getString(R.string.edText_initial_diners), 1f).toInt()
        if (diners == 0) binding.edTextDiner.setText(getString(R.string.edText_initial_diners))

        createTipCalculator()
        setTextDinerGroup()
    }

    private fun captureValues() {
        bill = captureValue(binding.edTextBill, getString(R.string.edText_initial_amount))
        percentage = captureValue(binding.edTextTipPercentage, getString(R.string.edText_tip_percentage), 10f)
        diners = captureValue(binding.edTextDiner, getString(R.string.edText_initial_diners), 1f).toInt()
    }

    private fun captureValue(editText: EditText, text: String, defaultValue: Float = 0f): Float =
            if (editText.isNotEmpty()) {
                editText.text.toString().toFloat()
            } else {
                editText.setText(text)
                editText.selectAll()
                defaultValue
            }

    //Reset bill to default amount.
    private fun resetBill() {
        binding.edTextBill.setText(R.string.edText_initial_amount)
        binding.edTextBill.requestFocus()
        binding.edTextBill.selectAll()
        binding.edTextTipPercentage.setText(R.string.edText_tip_percentage)
    }

    private fun resetDiner() {
        binding.edTextDiner.setText(getString(R.string.edText_initial_diners))
        binding.edTextDiner.requestFocus()
        binding.edTextDiner.selectAll()
    }

    private fun setText() {
        setTextBillGroup()
        setTextDinerGroup()
    }

    private fun setTextBillGroup() {
        binding.edTextTip.setText(String.format("%.2f", tipCalculator.calculateTip()))
        binding.edTextTotal.setText(String.format("%.2f", tipCalculator.calculateTotal()))
    }


    private fun setTextDinerGroup() {
        binding.edTextDiner.setText(String.format("%.2f", tipCalculator.calculatePerDinner()))
        binding.edTextRounded.setText(String.format("%.2f", tipCalculator.calculatePerDinnerRounded()))
    }

    private fun createTipCalculator() {
        tipCalculator = TipCalculator( bill = bill, percentage = percentage, dinners = diners)
    }

}