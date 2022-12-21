package hien.android.joblogic.domain.repository

import hien.android.joblogic.data.model.entity.ItemToSell
import hien.android.joblogic.domain.base.RepositoryResult

interface SellRepository {
    suspend fun getItemsToSell(): RepositoryResult<List<ItemToSell>>
}