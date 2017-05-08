package spike.tornado.frontend

import javafx.geometry.Pos
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val toolbox by cssclass()
    }

    init {
        toolbox {
            alignment = Pos.CENTER_RIGHT
            padding = box(5.px)
        }
    }
}