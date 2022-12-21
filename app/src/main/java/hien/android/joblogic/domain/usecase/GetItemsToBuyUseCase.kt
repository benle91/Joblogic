package hien.android.joblogic.domain.usecase

import hien.android.joblogic.data.model.remote.ItemBuyResponse
import hien.android.joblogic.domain.base.BaseFlowUseCase
import hien.android.joblogic.domain.base.RepositoryResult
import hien.android.joblogic.domain.base.UseCaseResult
import hien.android.joblogic.domain.repository.ApiRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetItemsToBuyUseCase constructor(
    private val repository: ApiRepository,
    private val executionThread: CoroutineDispatcher? = null
) : BaseFlowUseCase<String, List<ItemBuyResponse>, List<ItemBuyResponse>>(executionThread) {

    override suspend fun execute(parameters: String): RepositoryResult<List<ItemBuyResponse>> {
        return repository.getItemsToBuy()
    }

    override suspend fun onSuccess(response: RepositoryResult.Success<List<ItemBuyResponse>>): UseCaseResult.Success<List<ItemBuyResponse>> {
        return UseCaseResult.Success(response.data ?: emptyList<ItemBuyResponse>())
    }

}