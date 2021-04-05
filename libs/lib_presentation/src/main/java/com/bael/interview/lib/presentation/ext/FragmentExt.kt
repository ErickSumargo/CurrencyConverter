package com.bael.interview.lib.presentation.ext

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 * Created by ErickSumargo on 04/04/21.
 */

fun Fragment.readDrawable(@DrawableRes resId: Int): Drawable {
    return requireContext().drawable(resId)
}

fun Fragment.readText(@StringRes resId: Int): String {
    return requireContext().getString(resId)
}
