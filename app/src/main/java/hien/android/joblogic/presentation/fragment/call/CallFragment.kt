package hien.android.joblogic.presentation.fragment.call

import android.widget.Toast
import hien.android.joblogic.R
import hien.android.joblogic.databinding.FragmentCallBinding
import hien.android.joblogic.presentation.base.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CallFragment : BaseBindingFragment<FragmentCallBinding>() {

    companion object {
        fun newInstance() = CallFragment()
    }

    private val mViewModel by viewModel<CallViewModel>()

    override val layoutResourceId: Int
        get() = R.layout.fragment_call

    override fun onViewBindingCreated() {
        handleViewModel()
    }

    private fun handleViewModel() = with(mViewModel) {
        getCall()
        itemCallListResponse.observe(viewLifecycleOwner) { list ->
            Toast.makeText(requireContext(), list[0].name, Toast.LENGTH_LONG).show()
        }
    }


}