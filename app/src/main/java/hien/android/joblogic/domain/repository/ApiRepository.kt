package hien.android.joblogic.domain.repository

import hien.android.joblogic.data.model.remote.ItemBuyResponse
import hien.android.joblogic.data.model.remote.ItemCallResponse
import hien.android.joblogic.domain.base.RepositoryResult
import retrofit2.Response

interface ApiRepository {
    suspend fun getItemsToCall(): RepositoryResult<List<ItemCallResponse>>
    suspend fun getItemsToBuy(): RepositoryResult<List<ItemBuyResponse>>
}