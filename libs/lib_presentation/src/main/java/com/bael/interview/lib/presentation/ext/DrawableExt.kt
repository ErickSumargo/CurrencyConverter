package com.bael.interview.lib.presentation.ext

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat.getDrawable

/**
 * Created by ErickSumargo on 04/04/21.
 */

fun Context.drawable(@DrawableRes resId: Int): Drawable {
    val drawable = getDrawable(this, resId)
    return drawable ?: ColorDrawable()
}
