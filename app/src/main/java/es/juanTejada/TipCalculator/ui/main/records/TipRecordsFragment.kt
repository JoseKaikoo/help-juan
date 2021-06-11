package es.juanTejada.TipCalculator.ui.main.records

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import es.juanTejada.TipCalculator.R
import es.juanTejada.TipCalculator.databinding.TipRecordsFragmentBinding
import es.juanTejada.TipCalculator.model.TipAdapter
import es.juanTejada.TipCalculator.model.TipCalculator

class TipRecordsFragment : Fragment(R.layout.tip_records_fragment) {
    private val binding: TipRecordsFragmentBinding by lazy {
        TipRecordsFragmentBinding.bind(requireView())
    }
    private val listadapter: TipAdapter = TipAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        set()
    }

    private fun set() {
        setupRecycler()
    }

    private fun setupRecycler() {
        binding.lstTip.run {
            setHasFixedSize(true)
            adapter = listadapter
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun updateList(tip: ArrayList<TipCalculator>) {
        listadapter.submitList(tip)
    }
}