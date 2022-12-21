package hien.android.joblogic.presentation.fragment.home

import androidx.databinding.ViewDataBinding
import hien.android.joblogic.R
import hien.android.joblogic.presentation.base.BaseBindingFragment

class HomeFragment : BaseBindingFragment<ViewDataBinding>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_home

    override fun onViewBindingCreated() {

    }
}