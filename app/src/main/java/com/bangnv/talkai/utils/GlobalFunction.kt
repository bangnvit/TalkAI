package com.bangnv.talkai.utils

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.text.SpannableString
import android.text.style.StyleSpan
import android.graphics.Typeface
import android.text.Spannable
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.UnderlineSpan
import android.widget.TextView
import android.widget.Toast

object GlobalFunction {
    @JvmStatic
    fun hideSoftKeyboard(activity: Activity) {
        try {
            val inputMethodManager =
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
        }
    }

    @JvmStatic
    fun showSoftKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    // Global function to copy text to clipboard
    fun copyTextToClipboard(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", text)
        clipboard.setPrimaryClip(clip)

        // Optionally, show a Toast message to inform the user
        Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
    }

}