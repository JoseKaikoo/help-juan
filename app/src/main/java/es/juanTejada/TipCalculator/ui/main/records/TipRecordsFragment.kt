package es.juanTejada.TipCalculator.ui.main.records

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import es.juanTejada.TipCalculator.R
import es.juanTejada.TipCalculator.databinding.TipRecordsFragmentBinding

class TipRecordsFragment : Fragment(R.layout.tip_records_fragment) {
    private val binding: TipRecordsFragmentBinding by lazy {
        TipRecordsFragmentBinding.bind(requireView())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }
}