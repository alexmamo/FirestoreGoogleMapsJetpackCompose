package ro.alexmamo.firestoregooglemapsjetpackcompose.core

import android.util.Log
import ro.alexmamo.firestoregooglemapsjetpackcompose.core.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception?) = e?.apply {
            Log.e(TAG, stackTraceToString())
        }
    }
}