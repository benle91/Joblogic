package hien.android.joblogic.presentation.fragment.buy

import android.widget.TextView
import androidx.core.view.isGone
import hien.android.joblogic.R
import hien.android.joblogic.databinding.FragmentBuyBinding
import hien.android.joblogic.presentation.activity.MainViewModel
import hien.android.joblogic.presentation.adapter.BuyAdapter
import hien.android.joblogic.presentation.base.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.UnknownHostException

class BuyFragment : BaseBindingFragment<FragmentBuyBinding>() {

    companion object {
        fun newInstance() = BuyFragment()
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_buy

    private val mViewModel by viewModel<BuyViewModel>()
    private val mMainViewModel by activityViewModel<MainViewModel>()
    private val mAdapter by lazy { BuyAdapter() }

    override fun onViewBindingCreated() {
        handleEvent()
        setUpAdapter()
        handleViewModel()
    }

    private fun handleEvent() = binding?.run {
        ibBack.setOnClickListener {
            onCustomBackPresses()
        }
    }

    private fun handleViewModel() = with(mViewModel) {
        getItemsToBuy()
        itemToBuyResponseLiveData.observe(viewLifecycleOwner) {
            binding?.pbLoading?.isGone = true
            if (it.isEmpty()) {
                binding?.tvError?.isGone = false
                binding?.tvError?.setText("Empty Records", TextView.BufferType.NORMAL)
                return@observe
            }
            binding?.tvError?.isGone = true
            mAdapter.submitItems(it)
        }
        throwableLiveData.observe(viewLifecycleOwner) { throwable ->
            binding?.pbLoading?.isGone = true
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

    override fun onCustomBackPresses(): Boolean {
        mMainViewModel.onBackClick()
        return true
    }

}