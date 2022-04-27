package by.lifetech.test.repository

import android.content.Context
import by.lifetech.test.repository.model.ProductsModel
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.buffer
import okio.source
import timber.log.Timber

/**
 * Created by Artem Babuk on 27,апрель,2022
 * Skype: archiecrown
 * Telegram: @iBabuk
 */
class AssetsRepository(
    private val context: Context,
    private val moshi: Moshi
) {

    private val productsAdapter = moshi.adapter(ProductsModel::class.java)

    suspend fun getProducts(): Flow<ProductsModel?> {
        return flow {
            val stream = context
                .assets
                .open("products_list.json")

            var products: ProductsModel? = null

            JsonReader.of(stream.source().buffer()).use { reader ->
                products = productsAdapter.fromJson(reader)
            }
            emit(products)
        }
            .catch {
                Timber.e(it)
            }
            .flowOn(Dispatchers.IO)
    }
}