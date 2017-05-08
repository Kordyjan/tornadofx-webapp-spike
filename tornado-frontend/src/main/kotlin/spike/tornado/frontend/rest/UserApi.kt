package spike.tornado.frontend.rest

import io.reactivex.Observable
import retrofit2.http.GET
import spike.common.model.Person

interface UserApi {
    @GET("api/users")
    fun getUsers(): Observable<List<Pair<Int, Person>>>
}