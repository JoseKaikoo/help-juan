package es.juanTejada.TipCalculator.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.juanTejada.TipCalculator.databinding.TipItemBinding

class TipAdapter : ListAdapter<TipCalculator, TipAdapter.ViewHolder?>(TipItemDiffCallback) {
    inner class ViewHolder(private val item: TipItemBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bind(tip: TipCalculator) {
            item.lblName.text = tip.name
            item.lblDate.text = tip.date
            item.lblPercentage.text = tip.percentage.toString()
            val tip = "Bill: ${tip.bill} Diners: ${tip.dinners}"
            item.lblTip.text = tip
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            TipItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

object TipItemDiffCallback : DiffUtil.ItemCallback<TipCalculator>() {

    override fun areItemsTheSame(oldItem: TipCalculator, newItem: TipCalculator): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: TipCalculator, newItem: TipCalculator): Boolean =
        oldItem.id == newItem.id
                && oldItem.bill == newItem.bill
}