package spike.tornado.frontend.view

import com.github.thomasnield.rxkotlinfx.actionEvents
import javafx.scene.control.ListView
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import spike.tornado.frontend.Styles.Companion.toolbox
import spike.tornado.frontend.rest.RestController
import spike.tornado.frontend.rest.UserApi
import spike.tornado.frontend.utils.innerMap
import tornadofx.*

class UserView : View() {
    private val restController: RestController by inject()

    private var list: ListView<String> by singleAssign()

    private val userApi: UserApi by lazy {
        restController.retrofit.create(UserApi::class.java)
    }

    private val refreshGraphic by lazy {
        val image = Image(resources["refresh.png"], 30.0, 0.0, true, true, true)
        ImageView(image)
    }

    override val root = borderpane {
        center {
            list = listview()
        }

        bottom = vbox {
            addClass(toolbox)
            button(graphic = refreshGraphic) {
                actionEvents().switchMap { _ ->
                    userApi.getUsers().innerMap { it.second.name }
                }.subscribe {
                    list.items.setAll(it)
                }
            }
        }
    }
}
