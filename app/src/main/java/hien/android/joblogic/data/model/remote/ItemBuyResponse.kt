package hien.android.joblogic.data.model.remote


import com.google.gson.annotations.SerializedName

data class ItemBuyResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val _name: String?,
    @SerializedName("price")
    val _price: Int?,
    @SerializedName("quantity")
    val _quantity: Int?,
    @SerializedName("type")
    val type: Int?
) {

    /**
     * Avoid show null when binding data
     * */
    val name: String
    get() = _name.orEmpty()

    /**
     * Avoid show null when binding data
     * */
    val price: String
    get() = _price?.toString().orEmpty()

    /**
     * Avoid show null when binding data
     * */
    val quantity: String
    get() = _quantity?.toString().orEmpty()
}