package hien.android.joblogic.core.di

import hien.android.joblogic.core.network.RetrofitClient
import hien.android.joblogic.data.repository.ApiRepositoryImpl
import hien.android.joblogic.data.repository.ApiService
import hien.android.joblogic.domain.repository.ApiRepository
import hien.android.joblogic.domain.usecase.GetItemCallListUseCase
import hien.android.joblogic.presentation.fragment.call.CallViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single { RetrofitClient.getInstance() }
    single { get<Retrofit>().create(ApiService::class.java) }
    single<ApiRepository> { ApiRepositoryImpl(get()) }
    factory { GetItemCallListUseCase(get()) }
    viewModel { CallViewModel(get()) }
}