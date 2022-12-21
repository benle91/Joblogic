package hien.android.joblogic.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import hien.android.joblogic.R
import hien.android.joblogic.data.model.entity.ItemToSell
import hien.android.joblogic.databinding.ItemBuyBinding
import hien.android.joblogic.databinding.ItemSellBinding

class SellAdapter : RecyclerView.Adapter<SellViewHolder>() {

    private val items = arrayListOf<ItemToSell>()

    fun submitItems(list: List<ItemToSell>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellViewHolder {
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_sell,
                parent,
                false
            )
        return SellViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SellViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class SellViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemToSell) {
        (binding as? ItemSellBinding)?.run {
            data = item
        }
    }

}