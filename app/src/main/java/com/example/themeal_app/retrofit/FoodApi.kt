import com.example.themeal_app.model.allData
import retrofit2.Call


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    @GET("categories.php")
    suspend fun getCategories(): Response<allData>

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("i") category: String): Response<allData>

    @GET("random.php")
    suspend fun getRandomMeal(): Response<allData>

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id: String): Response<allData>

    @GET("search.php")
    suspend fun getMealByName(@Query("s") name: String): Response<allData>
}
