package hien.android.joblogic.domain.usecase

import hien.android.joblogic.data.model.remote.ItemToBuyResponse
import hien.android.joblogic.domain.base.BaseFlowUseCase
import hien.android.joblogic.domain.base.RepositoryResult
import hien.android.joblogic.domain.base.UseCaseResult
import hien.android.joblogic.domain.repository.ApiRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetItemsToBuyUseCase constructor(
    private val repository: ApiRepository,
    private val executionThread: CoroutineDispatcher? = null
) : BaseFlowUseCase<String, List<ItemToBuyResponse>, List<ItemToBuyResponse>>(executionThread) {

    override suspend fun execute(parameters: String): RepositoryResult<List<ItemToBuyResponse>> {
        return repository.getItemsToBuy()
    }

    override suspend fun onSuccess(response: RepositoryResult.Success<List<ItemToBuyResponse>>): UseCaseResult.Success<List<ItemToBuyResponse>> {
        return UseCaseResult.Success(response.data ?: emptyList<ItemToBuyResponse>())
    }

}