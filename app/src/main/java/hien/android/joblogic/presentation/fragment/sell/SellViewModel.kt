package hien.android.joblogic.presentation.fragment.sell

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hien.android.joblogic.data.model.entity.ItemToSell
import hien.android.joblogic.domain.base.onSuccess
import hien.android.joblogic.domain.base.onThrowable
import hien.android.joblogic.domain.usecase.GetItemsToSellUseCase
import kotlinx.coroutines.flow.launchIn

class SellViewModel constructor(
    private val getItemsToSellUseCase: GetItemsToSellUseCase
) : ViewModel() {

    private val _itemsToSellLiveData = MutableLiveData<List<ItemToSell>>()
    val itemsToSellLiveData: LiveData<List<ItemToSell>> = _itemsToSellLiveData

    private val _throwableLiveData = MutableLiveData<Throwable>()
    val throwableLiveData: LiveData<Throwable> = _throwableLiveData

    fun getItemsToSell() {
        getItemsToSellUseCase("").onSuccess {
            _itemsToSellLiveData.postValue(it)
        }.onThrowable {
            _throwableLiveData.postValue(it)
            Log.i("AAAA", it.toString())
        }.launchIn(viewModelScope)
    }

}