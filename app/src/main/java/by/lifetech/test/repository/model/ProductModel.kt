package by.lifetech.test.repository.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Artem Babuk on 27,апрель,2022
 * Skype: archiecrown
 * Telegram: @iBabuk
 */
@JsonClass(generateAdapter = true)
class ProductModel(
    @Json(name = "product_id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "price") val price: Int,
    @Json(name = "image") val image: String
)