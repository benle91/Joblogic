package hien.android.joblogic.data.model.remote

import com.google.gson.annotations.SerializedName

enum class ItemTransactionType {
    @SerializedName("1")
    BUY,

    @SerializedName("2")
    SELL
}