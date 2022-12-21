package hien.android.joblogic.data.model.remote

import com.google.gson.annotations.SerializedName

data class ItemCallResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    private val _name: String?,
    @SerializedName("number")
    private val _number: String?
) {
    val name: String
    get() = _name.orEmpty()

    val number: String
    get() = _number.orEmpty()
}