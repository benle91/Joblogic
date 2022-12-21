package hien.android.joblogic.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import hien.android.joblogic.MainCoroutineRule
import hien.android.joblogic.data.model.remote.ItemBuyResponse
import hien.android.joblogic.data.model.remote.ItemCallResponse
import hien.android.joblogic.domain.base.RepositoryResult
import hien.android.joblogic.domain.base.UseCaseResult
import hien.android.joblogic.domain.repository.ApiRepository
import hien.android.joblogic.domain.usecase.GetItemsToBuyUseCase
import hien.android.joblogic.runBlockingTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
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
        coEvery { repository.getItemsToBuy() }.answers { MockItemToBuyResponse.itemToBuy }
        val result = useCase("").toList()
        assert(result[0] is UseCaseResult.Loading)
        assert(result[1] is UseCaseResult.Success)
    }

}

object MockItemToBuyResponse {

    val itemToCall: RepositoryResult<List<ItemCallResponse>>
    get() = RepositoryResult.Success(listOf(
        ItemCallResponse(id = 1, _name = "Jason White", _number = "4324234"),
        ItemCallResponse(id = 2, _name = "Wasim Khan", _number = "2343243"),
        ItemCallResponse(id = 3, _name = "Amir Khan", _number = "3423423"),
    ))

    val itemToBuy: RepositoryResult<List<ItemBuyResponse>>
    get() = RepositoryResult.Success(listOf(
        ItemBuyResponse(id = 1, _name = "MacBook Pro", _price = 205000, _quantity = 1, type = 1),
        ItemBuyResponse(id = 2, _name = "iPad", _price = 20000, _quantity = 2, type = 1),
        ItemBuyResponse(id = 3, _name = "Power Bank", _price = 10000, _quantity = 1, type = 1),
    ))

}