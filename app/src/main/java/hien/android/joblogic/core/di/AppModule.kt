package hien.android.joblogic.core.di

import androidx.room.Room
import hien.android.joblogic.core.network.RetrofitClient
import hien.android.joblogic.core.room.AppDatabase
import hien.android.joblogic.data.api.ApiService
import hien.android.joblogic.data.dao.ItemToSellDao
import hien.android.joblogic.data.repository.ApiRepositoryImpl
import hien.android.joblogic.data.repository.SellRepositoryImpl
import hien.android.joblogic.domain.repository.ApiRepository
import hien.android.joblogic.domain.repository.SellRepository
import hien.android.joblogic.domain.usecase.GetItemsToBuyUseCase
import hien.android.joblogic.domain.usecase.GetItemsToCallUseCase
import hien.android.joblogic.domain.usecase.GetItemsToSellUseCase
import hien.android.joblogic.presentation.fragment.buy.BuyViewModel
import hien.android.joblogic.presentation.fragment.call.CallViewModel
import hien.android.joblogic.presentation.fragment.sell.SellViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "job"
        ).createFromAsset("job.db").fallbackToDestructiveMigration().build()
    }
    single<ItemToSellDao> { get<AppDatabase>().getItemToSellDao() }
    single { RetrofitClient.getInstance() }
    single { get<Retrofit>().create(ApiService::class.java) }
    single<ApiRepository> { ApiRepositoryImpl(get()) }
    single<SellRepository> { SellRepositoryImpl(get()) }
    factory { GetItemsToCallUseCase(get()) }
    factory { GetItemsToBuyUseCase(get()) }
    factory { GetItemsToSellUseCase(get()) }
    viewModel { CallViewModel(get()) }
    viewModel { BuyViewModel(get()) }
    viewModel { SellViewModel(get()) }
}