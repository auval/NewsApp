package com.auval.newsapp.model

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DataBindingAdapters {
    @BindingAdapter("asyncLoadImage")
    @JvmStatic
    fun asyncLoadImage(view: ImageView, imageUrl: String?) {
        Picasso.with(view.context)
            .load(imageUrl)
            .into(view)
    }

    @BindingAdapter("convertDateFormat")
    @JvmStatic
    fun convertDateFormat(view: TextView, date: String?) {
        val parsed = ZonedDateTime.parse(date)
        val formatted = parsed.format(DateTimeFormatter.RFC_1123_DATE_TIME)
        view.text = formatted
    }
}
