package hien.android.joblogic.domain.repository

import hien.android.joblogic.data.model.remote.ItemCallResponse
import hien.android.joblogic.data.model.remote.ItemToBuyResponse
import hien.android.joblogic.domain.base.RepositoryResult

interface ApiRepository {
    suspend fun getItemsToCall(): RepositoryResult<List<ItemCallResponse>>
    suspend fun getItemsToBuy(): RepositoryResult<List<ItemToBuyResponse>>
}