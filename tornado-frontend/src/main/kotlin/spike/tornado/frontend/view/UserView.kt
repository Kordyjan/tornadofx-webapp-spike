package spike.tornado.frontend.view

import com.github.thomasnield.rxkotlinfx.actionEvents
import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Orientation
import javafx.scene.control.ListView
import javafx.scene.control.Spinner
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import spike.common.model.Indexed
import spike.common.model.Person
import spike.tornado.frontend.Styles.Companion.newUserForm
import spike.tornado.frontend.Styles.Companion.toolbox
import spike.tornado.frontend.Styles.Companion.userListEntry
import spike.tornado.frontend.rest.RestController
import spike.tornado.frontend.rest.UserApi
import tornadofx.*

class UserView : View() {
    private val restController: RestController by inject()

    private var list: ListView<Indexed<Person>> by singleAssign()

    private val userApi: UserApi by lazy {
        restController.retrofit.create(UserApi::class.java)
    }

    private val refreshGraphic by lazy {
        val image = Image(resources["refresh.png"], 30.0, 0.0, true, true, true)
        ImageView(image)
    }

    private val moneyImage: Image by lazy {
        Image(resources["money.png"], 30.0, 0.0, true, true, true)
    }

    override val root = borderpane {
        center {
            list = listview {
                cellCache {
                    hbox {
                        addClass(userListEntry)

                        label(it.value.name) {
                            maxWidth = Double.MAX_VALUE
                            HBox.setHgrow(this, Priority.ALWAYS)
                        }
                        hbox {
                            imageview {
                                image = moneyImage
                            }
                            label(it.value.gold.toString())
                        }
                    }
                }
            }
        }

        bottom = hbox {
            addClass(toolbox)

            button(graphic = refreshGraphic) {
                actionEvents().switchMap { _ ->
                    userApi.getUsers()
                }.subscribe {
                    list.items.setAll(it)
                }
            }

            separator(Orientation.VERTICAL)

            hbox {
                var nameField: TextField by singleAssign()
                var moneyField: Spinner<Int> by singleAssign()

                addClass(newUserForm)
                form {
                    fieldset("Add user") {
                        field("Name") {
                            nameField = textfield()
                        }
                        field("Money") {
                            moneyField = spinner(0, 1000, 100, 10)
                        }
                    }
                }
                button("add") {
                    actionEvents().switchMap { _ ->
                        userApi.addUser(Person(nameField.text, moneyField.value.toLong()))
                                .toObservable()
                    }.switchMap {
                        userApi.getUsers()
                    }.subscribe {
                        list.items.setAll(it)
                    }
                }
            }

        }
    }
}
