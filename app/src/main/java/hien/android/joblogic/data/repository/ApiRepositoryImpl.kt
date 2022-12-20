package hien.android.joblogic.data.repository

import hien.android.joblogic.data.BaseHandleDataResponseSupporter
import hien.android.joblogic.data.model.remote.ItemCallResponse
import hien.android.joblogic.domain.base.RepositoryResult
import hien.android.joblogic.domain.repository.ApiRepository

class ApiRepositoryImpl constructor(private val service: ApiService) : ApiRepository,
    BaseHandleDataResponseSupporter {

    override suspend fun getItemsCallLists(): RepositoryResult<List<ItemCallResponse>> {
        return executeService { service.getItemsCallLists() }
    }
}