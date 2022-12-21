package hien.android.joblogic.presentation.fragment.buy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hien.android.joblogic.data.model.remote.ItemToBuyResponse
import hien.android.joblogic.domain.base.onSuccess
import hien.android.joblogic.domain.base.onThrowable
import hien.android.joblogic.domain.usecase.GetItemsToBuyUseCase
import kotlinx.coroutines.flow.launchIn

class BuyViewModel constructor(private val getItemsToBuyUseCase: GetItemsToBuyUseCase) :
    ViewModel() {

    private val _itemToBuyResponseLiveData = MutableLiveData<List<ItemToBuyResponse>>()
    val itemToBuyResponseLiveData: LiveData<List<ItemToBuyResponse>> = _itemToBuyResponseLiveData

    private val _throwableLiveData = MutableLiveData<Throwable>()
    val throwableLiveData: LiveData<Throwable> = _throwableLiveData

    fun getItemsToBuy() {
        getItemsToBuyUseCase("").onSuccess { list ->
            _itemToBuyResponseLiveData.postValue(list)
        }.onThrowable {
            _throwableLiveData.postValue(it)
        }
            .launchIn(viewModelScope)
    }

}