package hien.android.joblogic.presentation.activity

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isGone
import androidx.fragment.app.commit
import hien.android.joblogic.R
import hien.android.joblogic.presentation.fragment.buy.BuyFragment
import hien.android.joblogic.presentation.fragment.call.CallFragment
import hien.android.joblogic.presentation.fragment.sell.SellFragment

class MainActivity : AppCompatActivity(), OnClickListener {

    companion object {
        private const val MAIN_BACK_STACK = "main_back_stack"
    }

    private val mViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnCall).setOnClickListener(this)
        findViewById<Button>(R.id.btnBuy).setOnClickListener(this)
        findViewById<Button>(R.id.btnSell).setOnClickListener(this)
        mViewModel.onEventBackToHome.observe(this) {
            findViewById<CardView>(R.id.cvController).isGone = false
            navigateToFragment(-1)
        }
    }

    private fun navigateToFragment(@IdRes id: Int?) {
        supportFragmentManager.commit(allowStateLoss = true) {
            replace(R.id.fvcMain, when (id) {
                R.id.btnBuy -> BuyFragment.newInstance()
                R.id.btnSell -> SellFragment.newInstance()
                R.id.btnCall -> CallFragment.newInstance()
                else -> MainFragment()
            }, MAIN_BACK_STACK)
        }
    }

    override fun onClick(p0: View?) {
        findViewById<CardView>(R.id.cvController).isGone = true
        navigateToFragment(p0?.id)
    }

}