package spike.tornado.frontend

import javafx.geometry.Pos
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val toolbox by cssclass()
        val userListEntry by cssclass()
        val debugBorder by cssclass()
    }

    init {
        toolbox {
            alignment = Pos.CENTER_RIGHT
            padding = box(5.px)
        }

        userListEntry {
             label {
                fontSize = 20.px
            }
        }

        debugBorder {
            borderColor += box(Color.RED)
            backgroundColor += Color.GREEN
        }
    }
}