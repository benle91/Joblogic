package hien.android.joblogic.domain.usecase

import hien.android.joblogic.data.model.entity.ItemToSellEntity
import hien.android.joblogic.domain.base.BaseFlowUseCase
import hien.android.joblogic.domain.base.RepositoryResult
import hien.android.joblogic.domain.base.UseCaseResult
import hien.android.joblogic.domain.repository.SellRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetItemsToSellUseCase constructor(
    private val repository: SellRepository,
    executionThread: CoroutineDispatcher? = null
) : BaseFlowUseCase<String, List<ItemToSellEntity>, List<ItemToSellEntity>>(executionThread) {

    override suspend fun execute(parameters: String): RepositoryResult<List<ItemToSellEntity>> {
        return repository.getItemsToSell()
    }

    override suspend fun onSuccess(response: RepositoryResult.Success<List<ItemToSellEntity>>): UseCaseResult.Success<List<ItemToSellEntity>> {
        return UseCaseResult.Success(response.data ?: emptyList<ItemToSellEntity>())
    }

}