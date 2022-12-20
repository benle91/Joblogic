package hien.android.joblogic.domain.repository

import hien.android.joblogic.data.model.remote.ItemCallResponse
import hien.android.joblogic.domain.base.RepositoryResult
import retrofit2.Response

interface ApiRepository {
    suspend fun getItemsCallLists(): RepositoryResult<List<ItemCallResponse>>
}