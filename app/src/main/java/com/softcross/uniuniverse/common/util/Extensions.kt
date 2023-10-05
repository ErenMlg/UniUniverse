package com.softcross.uniuniverse.common.util

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.softcross.uniuniverse.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun Navigation.navigate(it: View, id: Int) {
    findNavController(it).navigate(id)
}

inline fun <T> Flow<T>.launchAndCollectIn(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline action: suspend CoroutineScope.(T) -> Unit
) = owner.lifecycleScope.launch {
    owner.repeatOnLifecycle(minActiveState) {
        collect {
            action(it)
        }
    }
}

fun Context.createCustomToast(message: String) {
    val toast = Toast(this)
    val layout = LayoutInflater.from(this).inflate(R.layout.view_toast_message, null)
    val textView = layout.findViewById<TextView>(R.id.tvInfo)
    textView.text = message
    toast.apply {
        setGravity(Gravity.BOTTOM, 0, 32)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}