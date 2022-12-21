package hien.android.joblogic.data

import hien.android.joblogic.domain.base.RepositoryResult
import retrofit2.HttpException
import retrofit2.Response

interface BaseHandleDataResponseSupporter {

    suspend fun <_Response> executeService(api: suspend () -> Response<_Response>): RepositoryResult<_Response> {
        try {
            api.invoke().let { response ->
                response.body()?.let { res ->
                    if (response.isSuccessful) {
                        return RepositoryResult.Success(res)
                    }
                }
                return RepositoryResult.Error(error = HttpException(response))
            }
        } catch (ex: Exception) {
            // Unknown
            return RepositoryResult.Error(ex)
        }
    }

    suspend fun <_Response> executeDao(api: suspend () -> _Response): RepositoryResult<_Response> {
        return try {
            val response = api.invoke()
            RepositoryResult.Success(response)
        } catch (ex: Exception) {
            // Unknown
            RepositoryResult.Error(ex)
        }
    }

}