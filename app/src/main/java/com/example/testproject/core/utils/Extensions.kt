package com.example.testproject.core.utils

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.testproject.R
import com.example.testproject.BuildConfig

fun EditText.showKeyboard() {
    val imm = ContextCompat.getSystemService(
        this.context,
        InputMethodManager::class.java
    ) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url: String?, isFromCache: Boolean = true) {
    try {
        if (isFromCache) {
            Glide.with(context)
                .load(url)
                //.error(R.drawable.img_placeholder_loading)
                //.thumbnail(Glide.with(this).load(R.drawable.img_placeholder_not_found))
                .into(this)
        } else {
            Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                //.error(R.drawable.img_placeholder_loading)
                //.thumbnail(Glide.with(this).load(R.drawable.img_placeholder_not_found))
                .into(this)
        }

    } catch (e: Exception) {
    }
}


fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}



fun Fragment.transitionAnim(): NavOptions.Builder {
    val navBuilder: NavOptions.Builder = NavOptions.Builder()
    navBuilder.setEnterAnim(R.anim.slide_left).setExitAnim(R.anim.slide_right)
        .setPopEnterAnim(R.anim.slide_left).setPopExitAnim(R.anim.slide_right)
    return navBuilder
}


