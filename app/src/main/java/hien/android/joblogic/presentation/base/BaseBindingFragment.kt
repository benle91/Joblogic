package hien.android.joblogic.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseBindingFragment<_ViewDataBinding : ViewDataBinding> : Fragment() {

    var binding: _ViewDataBinding? = null

    abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (!onCustomBackPresses()) {
                        this.isEnabled = false
                        activity?.onBackPressed()
                    }
                }

            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            layoutResourceId,
            container,
            false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.lifecycleOwner = this
        onViewBindingCreated()
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun onViewBindingCreated()

    protected open fun onCustomBackPresses(): Boolean {
        return false
    }

}