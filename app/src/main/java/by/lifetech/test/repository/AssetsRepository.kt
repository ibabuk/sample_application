package by.lifetech.test.repository

import android.content.Context
import by.lifetech.test.repository.model.EntityModel
import com.google.gson.Gson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.Okio
import okio.buffer
import okio.source
import timber.log.Timber
import java.io.Reader

/**
 * Created by Artem Babuk on 27,апрель,2022
 * Skype: archiecrown
 * Telegram: @iBabuk
 */
class AssetsRepository(
    private val context: Context,
    private val moshi: Moshi
) {

    suspend fun getProducts(): Flow<List<EntityModel>> {
        return flow {
            val list = ArrayList<EntityModel>()
            val stream = context
                .assets
                .open("products_list.json")

            JsonReader.of(stream.source().buffer()).use { reader ->
                reader.beginObject()
                if (reader.nextName() == "products") {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        reader.beginObject()
                        var id = ""
                        var name = ""
                        var price = 0
                        var image = ""
                        while (reader.hasNext()) {
                            when (reader.nextName()) {
                                "product_id" -> id = reader.nextString()
                                "name" -> name = reader.nextString()
                                "price" -> price = reader.nextInt()
                                "image" -> image = reader.nextString()
                            }
                        }
                        list.add(
                            EntityModel(
                                id = id,
                                name = name,
                                price = price,
                                image = image
                            )
                        )

                        reader.endObject()
                    }
                    reader.endArray()
                }
                reader.endObject()
            }
            emit(list)
        }
            .catch {
                Timber.e(it)
            }
            .flowOn(Dispatchers.IO)
    }
}