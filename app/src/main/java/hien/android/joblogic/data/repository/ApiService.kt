package hien.android.joblogic.data.repository

import hien.android.joblogic.data.model.remote.ItemCallResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("demo-1/call")
    suspend fun getItemsCallLists(): Response<List<ItemCallResponse>>
}