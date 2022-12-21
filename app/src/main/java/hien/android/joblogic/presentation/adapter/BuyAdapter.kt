package hien.android.joblogic.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import hien.android.joblogic.R
import hien.android.joblogic.data.model.remote.ItemBuyResponse
import hien.android.joblogic.databinding.ItemBuyBinding

class BuyAdapter : RecyclerView.Adapter<BuyViewHolder>() {

    private val items = arrayListOf<ItemBuyResponse>()

    fun submitItems(list: List<ItemBuyResponse>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_buy,
                parent,
                false
            )
        return BuyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class BuyViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemBuyResponse) {
        (binding as? ItemBuyBinding)?.run {
            data = item
        }
    }

}