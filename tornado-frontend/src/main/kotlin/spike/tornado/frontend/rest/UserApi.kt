package spike.tornado.frontend.rest

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import spike.common.model.Indexed
import spike.common.model.Person

interface UserApi {
    @GET("api/users")
    fun getUsers(): Observable<List<Indexed<Person>>>

    @POST("/api/users")
    fun addUser(@Body user: Person): Single<Boolean>
}