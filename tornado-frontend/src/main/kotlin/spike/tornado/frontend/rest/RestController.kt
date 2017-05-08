package spike.tornado.frontend.rest

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import tornadofx.*

internal class RestController : Controller() {
    var host: String by singleAssign()

    var port: String by singleAssign()

    val retrofit: Retrofit by lazy {
        val url = HttpUrl.Builder()
                .scheme("http")
                .host(host)
                .port(port.toInt()
                ).build()

        Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
                .build()
    }
}