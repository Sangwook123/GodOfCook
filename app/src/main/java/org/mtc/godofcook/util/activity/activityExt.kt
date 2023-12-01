package org.mtc.godofcook.util.activity

import android.app.Activity
import android.view.View
import org.mtc.godofcook.util.context.hideKeyboard

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}