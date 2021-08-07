
import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("count")
    var count: Int,
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("success")
    var success: Boolean
)