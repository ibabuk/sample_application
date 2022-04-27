package by.lifetech.test.controller.model

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import by.lifetech.test.R
import by.lifetech.test.entity.SimpleEntity
import by.lifetech.test.glide.GlideApp
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.squareup.picasso.Picasso
import timber.log.Timber

/**
 * Created by Artem Babuk on 27,апрель,2022
 * Skype: archiecrown
 * Telegram: @iBabuk
 */
@EpoxyModelClass
abstract class SimpleModel : EpoxyModelWithHolder<SimpleModel.SimpleHolder>() {

    @EpoxyAttribute
    var entity: SimpleEntity? = null

    override fun getDefaultLayout(): Int = R.layout.simple_view

    override fun bind(holder: SimpleHolder) {
        super.bind(holder)
        holder.bind(entity)
    }

    class SimpleHolder : EpoxyHolder() {

        private lateinit var image: ImageView
        private lateinit var text: TextView
        private lateinit var price: TextView

        override fun bindView(itemView: View) {
            image = itemView.findViewById(R.id.image)
            text = itemView.findViewById(R.id.name)
            price = itemView.findViewById(R.id.price)
        }

        fun bind(entity: SimpleEntity?) {
            GlideApp.with(image)
                .load(entity?.image)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(image)

            text.text = entity?.name
            price.text = entity?.price.toString()
        }
    }
}