package hien.android.joblogic.data.repository

import hien.android.joblogic.data.BaseHandleDataResponseSupporter
import hien.android.joblogic.data.dao.ItemToSellDao
import hien.android.joblogic.data.model.entity.ItemToSell
import hien.android.joblogic.domain.base.RepositoryResult
import hien.android.joblogic.domain.repository.SellRepository

class SellRepositoryImpl constructor(
    private val dao: ItemToSellDao
) : SellRepository, BaseHandleDataResponseSupporter {
    override suspend fun getItemsToSell(): RepositoryResult<List<ItemToSell>> {
        return executeDao { dao.getAll() }
    }
}