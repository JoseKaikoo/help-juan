package es.juanTejada.TipCalculator.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import es.juanTejada.TipCalculator.R
import es.juanTejada.TipCalculator.databinding.TipFragmentBinding
import es.juanTejada.TipCalculator.model.TipCalculator
import es.juanTejada.TipCalculator.model.isNotEmpty

class TipFragment : Fragment(R.layout.tip_fragment) {

    private val binding: TipFragmentBinding by lazy {
        TipFragmentBinding.bind(requireView())
    }
    private lateinit var tipCalculator: TipCalculator

    private val viewModel: TipViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()

        viewModel.bill.observe(viewLifecycleOwner, {

        })
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
        viewModel.setDiners(captureValue(binding.edTextDiner, getString(R.string.edText_initial_diners), 1f).toInt())
        if (viewModel.diners.value == 0) binding.edTextDiner.setText(getString(R.string.edText_initial_diners))
        createTipCalculator()
        setTextDinerGroup()
    }

    private fun captureValues() {
        viewModel.setBill(captureValue(binding.edTextBill, getString(R.string.edText_initial_amount)))
        viewModel.setPercentage(captureValue(binding.edTextTipPercentage, getString(R.string.edText_tip_percentage), 10f))
        viewModel.setDiners(captureValue(binding.edTextDiner, getString(R.string.edText_initial_diners), 1f).toInt())
    }

    private fun captureValue(editText: EditText, text: String, defaultValue: Float = 0f): Float {
        return if (editText.isNotEmpty()) {
            editText.text.toString().toFloat()
        } else {
            editText.setText(text)
            editText.selectAll()
            defaultValue
        }
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
        viewModel.saveBill()
    }

    //Reset bill to default amount.
    private fun resetBill() {
        viewModel.resetBill()
        binding.edTextBill.requestFocus()
        binding.edTextBill.selectAll()
    }

    private fun resetDiner() {
        viewModel.resetDiner()
        binding.edTextDiner.requestFocus()
        binding.edTextDiner.selectAll()
    }

}