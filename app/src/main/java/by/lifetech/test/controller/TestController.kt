package by.lifetech.test.controller

import by.lifetech.test.controller.model.simple
import by.lifetech.test.entity.SimpleEntity
import com.airbnb.epoxy.EpoxyController

/**
 * Created by Artem Babuk on 27,апрель,2022
 * Skype: archiecrown
 * Telegram: @iBabuk
 */
class TestController : EpoxyController() {

    private val entities = ArrayList<SimpleEntity>()

    fun setList(list: List<SimpleEntity>) {
        entities.addAll(list)
        requestModelBuild()
    }

    override fun buildModels() {
        entities.forEach {
            simple {
                id(it.id)
                entity(it)
            }
        }
    }
}