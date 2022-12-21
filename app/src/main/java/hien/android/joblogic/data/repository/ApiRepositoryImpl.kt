package hien.android.joblogic.data.repository

import hien.android.joblogic.data.BaseHandleDataResponseSupporter
import hien.android.joblogic.data.model.remote.ItemBuyResponse
import hien.android.joblogic.data.model.remote.ItemCallResponse
import hien.android.joblogic.domain.base.RepositoryResult
import hien.android.joblogic.domain.repository.ApiRepository

class ApiRepositoryImpl constructor(private val service: ApiService) : ApiRepository,
    BaseHandleDataResponseSupporter {

    override suspend fun getItemsToCall(): RepositoryResult<List<ItemCallResponse>> {
        return executeService { service.getItemsToCall() }
    }

    override suspend fun getItemsToBuy(): RepositoryResult<List<ItemBuyResponse>> {
        return executeService { service.getItemsToBuy() }
    }
}