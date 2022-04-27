package by.lifetech.test.repository.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Artem Babuk on 27,апрель,2022
 * Skype: archiecrown
 * Telegram: @iBabuk
 */
@JsonClass(generateAdapter = true)
data class ProductsModel(@Json(name = "products") val products: List<ProductModel>)