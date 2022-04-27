package by.lifetech.test.interactor

import by.lifetech.test.entity.SimpleEntity
import by.lifetech.test.repository.AssetsRepository
import by.lifetech.test.repository.model.ProductModel
import by.lifetech.test.repository.model.ProductsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Artem Babuk on 27,апрель,2022
 * Skype: archiecrown
 * Telegram: @iBabuk
 */
class GetTestInteractor(
    private val repository: AssetsRepository,
    private val mapper: GetTestMapper
) {

    suspend fun getModels(): Flow<List<SimpleEntity>> {
        return repository
            .getProducts()
            .map {
                mapper.mapModels(it)
            }
    }
}

class GetTestMapper {

    fun mapModels(productsModel: ProductsModel?): List<SimpleEntity> {
        val mapList = ArrayList<SimpleEntity>()
        productsModel?.products?.forEach {
            mapList.add(
                SimpleEntity(
                    id = it.id,
                    name = it.name,
                    price = it.price,
                    image = it.image
                )
            )
        }
        return mapList
    }
}