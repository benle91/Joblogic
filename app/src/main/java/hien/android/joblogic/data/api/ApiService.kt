package hien.android.joblogic.data.api

import hien.android.joblogic.data.model.remote.ItemCallResponse
import hien.android.joblogic.data.model.remote.ItemToBuyResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("demo-1/call")
    suspend fun getItemsToCall(): Response<List<ItemCallResponse>>

    @GET("demo-1/buy")
    suspend fun getItemsToBuy(): Response<List<ItemToBuyResponse>>
}