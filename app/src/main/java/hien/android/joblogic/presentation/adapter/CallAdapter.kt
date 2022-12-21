package hien.android.joblogic.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import hien.android.joblogic.R
import hien.android.joblogic.data.model.remote.ItemCallResponse
import hien.android.joblogic.databinding.ItemCallBinding

class CallAdapter : RecyclerView.Adapter<CallViewHolder>() {

    private val items = arrayListOf<ItemCallResponse>()

    fun submitItems(list: List<ItemCallResponse>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallViewHolder {
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_call,
                parent,
                false
            )
        return CallViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CallViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class CallViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemCallResponse) {
        (binding as? ItemCallBinding)?.run {
            data = item
        }
    }

}