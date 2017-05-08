package spike.tornado.frontend

import javafx.stage.Stage
import spike.tornado.frontend.rest.RestController
import spike.tornado.frontend.view.UserView
import tornadofx.*

class ClientApp : App(UserView::class, Styles::class) {
    private val restController: RestController by inject()

    override fun start(stage: Stage) {
        super.start(stage)
        restController.host = parameters.unnamed[0]
        restController.port = parameters.unnamed[1]
    }
}

