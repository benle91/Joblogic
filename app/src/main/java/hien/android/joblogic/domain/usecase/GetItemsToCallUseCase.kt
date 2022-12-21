package hien.android.joblogic.domain.usecase

import hien.android.joblogic.data.model.remote.ItemCallResponse
import hien.android.joblogic.domain.base.BaseFlowUseCase
import hien.android.joblogic.domain.base.RepositoryResult
import hien.android.joblogic.domain.base.UseCaseResult
import hien.android.joblogic.domain.repository.ApiRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetItemsToCallUseCase constructor(
    private val repository: ApiRepository,
    executionThread: CoroutineDispatcher? = null
) : BaseFlowUseCase<String, List<ItemCallResponse>, List<ItemCallResponse>>(executionThread) {

    override suspend fun execute(parameters: String): RepositoryResult<List<ItemCallResponse>> {
        return repository.getItemsToCall()
    }

    override suspend fun onSuccess(response: RepositoryResult.Success<List<ItemCallResponse>>): UseCaseResult.Success<List<ItemCallResponse>> {
        return UseCaseResult.Success(response.data ?: emptyList<ItemCallResponse>())
    }

}