package com.example.foodiez.bindingAdapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.foodiez.R

class RecipesRowBinding {
    companion object{

        @BindingAdapter("setNumberOfLikes")
        @JvmStatic
        fun setNumberOfLikes(textView: TextView, likes: Int) {
            textView.text = likes.toString()
        }

        @BindingAdapter("setTimer")
        @JvmStatic
        fun setTimer(textView: TextView, time: Int) {
            textView.text = time.toString()
        }

        @BindingAdapter("setVeganColor")
        @JvmStatic
        fun setVeganColor(view: View, isvegan: Boolean) {
            if(isvegan) {
                when(view) {
                    is TextView -> {
                        view.setTextColor(
                            ContextCompat.getColor(view.context, R.color.green)
                        )
                    }
                    is ImageView -> {
                        view.setColorFilter(
                            ContextCompat.getColor(view.context, R.color.green)
                        )
                    }
                }
            }
        }

        @BindingAdapter("loadImageFromURL")
        @JvmStatic
        fun loadImageFromURL(imageView: ImageView, imageURL: String) {
            imageView.load(imageURL) {
                crossfade(600)
            }
        }
    }
}