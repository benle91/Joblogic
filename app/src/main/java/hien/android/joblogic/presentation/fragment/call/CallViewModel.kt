package hien.android.joblogic.presentation.fragment.call

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hien.android.joblogic.data.model.remote.ItemCallResponse
import hien.android.joblogic.domain.base.onSuccess
import hien.android.joblogic.domain.base.onThrowable
import hien.android.joblogic.domain.usecase.GetItemCallListUseCase
import kotlinx.coroutines.flow.launchIn

class CallViewModel constructor(
    private val getItemCallListUseCase: GetItemCallListUseCase
) : ViewModel() {

    private val _itemCallListResponse = MutableLiveData<List<ItemCallResponse>>()
    val itemCallListResponse: LiveData<List<ItemCallResponse>> = _itemCallListResponse

    fun getCall() {
        getItemCallListUseCase("").onSuccess {
            _itemCallListResponse.postValue(it)
        }.onThrowable {

        }.launchIn(viewModelScope)
    }

}