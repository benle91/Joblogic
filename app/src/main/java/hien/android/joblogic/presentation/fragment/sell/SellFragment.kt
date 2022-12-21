package hien.android.joblogic.presentation.fragment.sell

import android.widget.TextView
import androidx.core.view.isGone
import hien.android.joblogic.R
import hien.android.joblogic.databinding.FragmentSellBinding
import hien.android.joblogic.presentation.activity.MainViewModel
import hien.android.joblogic.presentation.adapter.SellAdapter
import hien.android.joblogic.presentation.base.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.UnknownHostException

class SellFragment : BaseBindingFragment<FragmentSellBinding>() {

    companion object {
        fun newInstance() = SellFragment()
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_sell

    private val mViewModel by viewModel<SellViewModel>()
    private val mMainViewModel by activityViewModel<MainViewModel>()
    private val mAdapter by lazy { SellAdapter() }

    override fun onViewBindingCreated() {
        handleEvent()
        setUpAdapter()
        handleViewModel()
    }

    private fun handleEvent() = binding?.run {
        ibBack.setOnClickListener {
            mMainViewModel.onBackClick()
        }
    }

    private fun handleViewModel() = with(mViewModel) {
        getItemsToSell()
        itemsToSellLiveData.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding?.tvError?.isGone = false
                binding?.tvError?.setText("Empty Records", TextView.BufferType.NORMAL)
                return@observe
            }
            binding?.tvError?.isGone = true
            mAdapter.submitItems(it)
        }
        throwableLiveData.observe(viewLifecycleOwner) { throwable ->
            binding?.tvError?.isGone = false
            binding?.tvError?.text = when (throwable) {
                is UnknownHostException -> "No Internet!!"
                else -> throwable.toString()
            }
        }
    }

    private fun setUpAdapter() = binding?.run {
        rvList.setHasFixedSize(true)
        rvList.adapter = mAdapter
    }
}