package es.juanTejada.TipCalculator.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import es.juanTejada.TipCalculator.R
import es.juanTejada.TipCalculator.data.AppDatabase
import es.juanTejada.TipCalculator.databinding.TipFragmentBinding
import es.juanTejada.TipCalculator.model.TipRepository
import es.juanTejada.TipCalculator.model.isNotEmpty


class TipFragment : Fragment(R.layout.tip_fragment) {

    private val binding: TipFragmentBinding by lazy {
        TipFragmentBinding.bind(requireView())
    }

    private val navController: NavController by lazy { findNavController() }
    private val alertDialog by lazy {
        AlertDialog.Builder(requireContext())
    }

    private val viewModel: TipViewModel by viewModels {
        TipViewModelFactory(TipRepository(AppDatabase.getInstance(requireContext()).tipDao))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupViews()
        viewModel.total.observe(viewLifecycleOwner, {
            binding.edTextTotal.setText(String.format("%.2f", it))
        })

        viewModel.tip.observe(viewLifecycleOwner, {
           binding.edTextTip.setText(String.format("%.2f", it))
        })

        viewModel.dinnerAmount.observe(viewLifecycleOwner, {
            binding.edTextPerDiner.setText(String.format("%.2f", it))
        })

        viewModel.dinersRounded.observe(viewLifecycleOwner, {
          binding.edTextRounded.setText(String.format("%.2f", it))
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
        binding.toolbar.inflateMenu(R.menu.main_mnu)
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.savemnu -> save()
                R.id.recordsmnu -> navToRecords()
            }
            return@setOnMenuItemClickListener true
        }
        binding.btnResetBill.setOnClickListener { resetBill() }
        binding.btnResetDiners.setOnClickListener { resetDiner() }
        binding.edTextBill.requestFocus()
    }


    private fun calculateBill() {
        captureValues()
    }

    private fun calculatePercentage() {
        captureValues()
    }

    private fun calculateDiners() {
        viewModel.setDiners(
            captureValue(
                binding.edTextDiner,
                getString(R.string.edText_initial_diners),
                1f
            ).toInt()
        )
        if (viewModel.diners.value == 0) binding.edTextDiner.setText(getString(R.string.edText_initial_diners))
    }

    private fun captureValues() {
        viewModel.setBill(captureValue(binding.edTextBill, getString(R.string.edText_initial_amount)))
        viewModel.setPercentage(captureValue(binding.edTextTipPercentage, getString(R.string.edText_tip_percentage), 0.00f))
        viewModel.setDiners(captureValue(binding.edTextDiner, getString(R.string.edText_initial_diners), 1f).toInt())
    }

    private fun captureValue(editText: EditText, text: String, defaultValue: Float = 0f): Float {
        return if (editText.isNotEmpty() ) {
            val texto = editText.text.toString()
            try {
                texto.toFloat()
            } catch (e: NumberFormatException) {
                editText.setText(text)
                editText.selectAll()
                defaultValue
            }
        } else {
            editText.setText(text)
            editText.selectAll()
            defaultValue
        }
    }

    private fun createTipCalculator() {
        var name = ""
        val input: EditText = EditText(requireContext())
        alertDialog
            .setView(input)
            .setTitle("Restaurant")
            .setMessage("enter name of the restaurant")
            .setPositiveButton("Save") { _, _ ->
                Log.d("pollo",input.text.toString())
                viewModel.saveBill(input.text.toString())
            }
            .create()
            .show()

    }

    //Reset bill to default amount.
    private fun resetBill() {
        viewModel.resetBill()
        binding.edTextBill.setText(String.format("%.2f", viewModel.bill.value))
        binding.edTextBill.requestFocus()
        binding.edTextBill.selectAll()
    }

    private fun resetDiner() {
        viewModel.resetDiner()
        binding.edTextDiner.requestFocus()
        binding.edTextDiner.selectAll()
    }

    private fun navToRecords() {
        navController.navigate(R.id.tipRecordsFragment)
    }

    private fun save() {
        createTipCalculator()
        resetBill()
        resetDiner()
    }
}