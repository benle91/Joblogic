package hien.android.joblogic.domain.usecase

import hien.android.joblogic.data.model.entity.ItemToSell
import hien.android.joblogic.domain.base.BaseFlowUseCase
import hien.android.joblogic.domain.base.RepositoryResult
import hien.android.joblogic.domain.base.UseCaseResult
import hien.android.joblogic.domain.repository.SellRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetItemsToSellUseCase constructor(
    private val repository: SellRepository,
    private val executionThread: CoroutineDispatcher? = null
) : BaseFlowUseCase<String, List<ItemToSell>, List<ItemToSell>>(executionThread) {

    override suspend fun execute(parameters: String): RepositoryResult<List<ItemToSell>> {
        return repository.getItemsToSell()
    }

    override suspend fun onSuccess(response: RepositoryResult.Success<List<ItemToSell>>): UseCaseResult.Success<List<ItemToSell>> {
        return UseCaseResult.Success(response.data ?: emptyList<ItemToSell>())
    }

}