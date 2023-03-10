package hien.android.joblogic.presentation.fragment.call

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hien.android.joblogic.data.model.remote.ItemCallResponse
import hien.android.joblogic.domain.base.onSuccess
import hien.android.joblogic.domain.base.onThrowable
import hien.android.joblogic.domain.usecase.GetItemsToCallUseCase
import kotlinx.coroutines.flow.launchIn

class CallViewModel constructor(
    private val getItemCallListUseCase: GetItemsToCallUseCase
) : ViewModel() {

    private val _itemsToCallLiveData = MutableLiveData<List<ItemCallResponse>>()
    val itemsToCallLiveData: LiveData<List<ItemCallResponse>> = _itemsToCallLiveData

    private val _throwableLiveData = MutableLiveData<Throwable>()
    val throwableLiveData: LiveData<Throwable> = _throwableLiveData

    fun getItemsToCall() {
        getItemCallListUseCase("").onSuccess {
            _itemsToCallLiveData.postValue(it)
        }.onThrowable {
            _throwableLiveData.postValue(it)
        }.launchIn(viewModelScope)
    }

}