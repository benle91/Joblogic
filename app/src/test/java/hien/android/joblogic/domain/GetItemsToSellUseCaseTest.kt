package hien.android.joblogic.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import hien.android.joblogic.MainCoroutineRule
import hien.android.joblogic.data.model.entity.ItemToSellEntity
import hien.android.joblogic.domain.base.RepositoryResult
import hien.android.joblogic.domain.base.UseCaseResult
import hien.android.joblogic.domain.repository.SellRepository
import hien.android.joblogic.domain.usecase.GetItemsToSellUseCase
import hien.android.joblogic.runBlockingTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetItemsToSellUseCaseTest {

    @get:Rule
    var coroutinesRule = MainCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var repository: SellRepository
    private lateinit var useCase: GetItemsToSellUseCase

    private val itemToSell: RepositoryResult<List<ItemToSellEntity>>
        get() = RepositoryResult.Success(
            listOf(
                ItemToSellEntity(
                    id = 1,
                    name = "MacBook Pro",
                    price = "205000",
                    quantity = 1,
                    type = 2
                )
            )
        )

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetItemsToSellUseCase(repository, coroutinesRule.testDispatcher)
    }

    @Test
    fun `test get items to call with throwable response`() = coroutinesRule.runBlockingTest {
        coEvery { repository.getItemsToSell() }.answers { throw Throwable() }
        val result = useCase("").toList()
        assert(result[0] is UseCaseResult.Loading)
        assert(result[1] is UseCaseResult.ErrorThrowable)
    }

    @Test
    fun `test get items to call with data response`() = coroutinesRule.runBlockingTest {
        coEvery { repository.getItemsToSell() }.answers { itemToSell }
        val result = useCase("").toList()
        assert(result[0] is UseCaseResult.Loading)
        assert(result[1] is UseCaseResult.Success)
    }

}