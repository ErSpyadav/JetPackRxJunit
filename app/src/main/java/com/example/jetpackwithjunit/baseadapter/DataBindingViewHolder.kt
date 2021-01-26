package com.example.jetpackwithjunit.baseadapter

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackwithjunit.utils.CircleTransform
import com.squareup.picasso.Picasso

class DataBindingViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Any, listener: Any?) {
//        binding.setVariable(
//            BR.item, item)
//        binding.setVariable(BR.listener, listener)
//        binding.executePendingBindings()
    }

    companion object {
        private const val DECIMAL_REDUCTION_RATIO: Float = 0.78f

        @BindingAdapter("srcCompat")
        @JvmStatic
        fun setImage(imageView: ImageView, resourceId: Int?) {
            if (resourceId != null) {
                val drawable: Drawable? =
                    AppCompatResources.getDrawable(imageView.context, resourceId)
                imageView.setImageDrawable(drawable)
            } else {
                imageView.setImageResource(0)
            }
        }

        @BindingAdapter("background")
        @JvmStatic
        fun setBackground(view: View, resourceId: Int?) {
            if (resourceId != null) {
                view.setBackgroundResource(resourceId)
            } else {
                view.setBackgroundResource(0)
            }
        }

        @BindingAdapter("colorText")
        @JvmStatic
        fun setTextColor(textView: TextView, resourceId: Int?) {
            if (resourceId != null && resourceId != 0) {
                textView.setTextColor(ContextCompat.getColor(textView.context, resourceId))
            } else {
                textView.setTextColor(0)
            }
        }


        @BindingAdapter("circleImageUrl")
        @JvmStatic
        fun setCircleImageUrl(imageView: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                Picasso.get()
                    .load(url)
                    .noFade()
                    .transform(CircleTransform())
                    .into(imageView)
            }
        }

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setImageUrl(imageView: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                Picasso.get().load(url)
                    .noFade()
                    .transform(CircleTransform())
                    .into(imageView)
            }
        }


        @BindingAdapter("android:typeface")
        @JvmStatic
        fun setTypeface(textView: TextView, style: String) {
            when (style) {
                "bold" -> textView.setTypeface(null, Typeface.BOLD)
                else -> textView.setTypeface(null, Typeface.NORMAL)
            }
        }

        @JvmStatic
        @BindingAdapter("isBold")
        fun setTypeface(view: TextView, isBold: Boolean) {
            if (isBold) {
                view.setTypeface(view.typeface, Typeface.BOLD)
            } else {
                view.setTypeface(view.typeface, Typeface.NORMAL)
            }
        }

    }
}
