package hien.android.joblogic.domain.usecase

import hien.android.joblogic.data.model.remote.ItemCallResponse
import hien.android.joblogic.domain.base.BaseFlowUseCase
import hien.android.joblogic.domain.base.RepositoryResult
import hien.android.joblogic.domain.base.UseCaseResult
import hien.android.joblogic.domain.repository.ApiRepository

class GetItemsToCallUseCase constructor(
    private val repository: ApiRepository
) : BaseFlowUseCase<String, List<ItemCallResponse>, List<ItemCallResponse>>() {

    override suspend fun execute(parameters: String): RepositoryResult<List<ItemCallResponse>> {
        return repository.getItemsToCall()
    }

    override suspend fun onSuccess(response: RepositoryResult.Success<List<ItemCallResponse>>): UseCaseResult.Success<List<ItemCallResponse>> {
        return UseCaseResult.Success(response.data ?: emptyList<ItemCallResponse>())
    }

}