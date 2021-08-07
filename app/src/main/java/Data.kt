
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("address")
    var address: String,
    @SerializedName("createdAt")
    var createdAt: String,
    @SerializedName("cuisine")
    var cuisine: String,
    @SerializedName("deliveryInfo")
    var deliveryInfo: String,
    @SerializedName("deliveryTime")
    var deliveryTime: String,
    @SerializedName("district")
    var district: String,
    @SerializedName("_id")
    var id: String,
    @SerializedName("imageUrl")
    var imageUrl: String,
    @SerializedName("meals")
    var meals: List<Any>,
    @SerializedName("minDeliveryFee")
    var minDeliveryFee: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("paymentMethods")
    var paymentMethods: String,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("slug")
    var slug: String,
    @SerializedName("user")
    var user: String,
    @SerializedName("__v")
    var v: Int,
    @SerializedName("website")
    var website: String
)