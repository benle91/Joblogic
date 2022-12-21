package hien.android.joblogic.presentation.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class MainViewModel: ViewModel() {

    private val _triggerBackClick = MutableStateFlow(false)

    val onEventBackToHome = _triggerBackClick.asLiveData()

    fun onBackClick() {
        _triggerBackClick.value = !_triggerBackClick.value
    }

}