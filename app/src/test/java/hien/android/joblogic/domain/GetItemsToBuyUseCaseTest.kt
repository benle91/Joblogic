package hien.android.joblogic.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import hien.android.joblogic.MainCoroutineRule
import hien.android.joblogic.data.model.remote.ItemToBuyResponse
import hien.android.joblogic.data.model.remote.ItemTransactionType
import hien.android.joblogic.domain.base.RepositoryResult
import hien.android.joblogic.domain.base.UseCaseResult
import hien.android.joblogic.domain.repository.ApiRepository
import hien.android.joblogic.domain.usecase.GetItemsToBuyUseCase
import hien.android.joblogic.runBlockingTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetItemsToBuyUseCaseTest {

    @get:Rule
    var coroutinesRule = MainCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var repository: ApiRepository
    private lateinit var useCase: GetItemsToBuyUseCase

    private val itemToBuy: RepositoryResult<List<ItemToBuyResponse>>
        get() = RepositoryResult.Success(
            listOf(
                ItemToBuyResponse(
                    id = 1,
                    _name = "MacBook Pro",
                    _price = "205000",
                    _quantity = 1,
                    type = ItemTransactionType.BUY
                )
            )
        )

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetItemsToBuyUseCase(repository, coroutinesRule.testDispatcher)
    }

    @Test
    fun `test get items to buy with throwable response`() = coroutinesRule.runBlockingTest {
        coEvery { repository.getItemsToBuy() }.answers { throw Throwable() }
        val result = useCase("").toList()
        assert(result[0] is UseCaseResult.Loading)
        assert(result[1] is UseCaseResult.ErrorThrowable)
    }

    @Test
    fun `test get items to buy with data response`() = coroutinesRule.runBlockingTest {
        coEvery { repository.getItemsToBuy() }.answers { itemToBuy }
        val result = useCase("").toList()
        assert(result[0] is UseCaseResult.Loading)
        assert(result[1] is UseCaseResult.Success)
    }

}