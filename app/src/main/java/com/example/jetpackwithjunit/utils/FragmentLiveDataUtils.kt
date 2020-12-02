package com.example.jetpackwithjunit.utils

import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.jetpackwithjunit.BuildConfig

private const val TAG = "FragmentLiveDataUtils"

fun Fragment.navigateObserver(
    directions: NavDirections,
    navOptions: NavOptions? = null
): Observer<in Unit> = Observer {
    if (BuildConfig.DEBUG) Log.d(TAG, "navigateObserver: received directions to ${resources.getResourceEntryName(directions.actionId)}")

    if (navOptions == null) findNavController().navigate(directions)
    else findNavController().navigate(directions, navOptions)
}

fun Fragment.navigateObserver(@IdRes resId: Int): Observer<in Unit> =
    Observer {
        if (BuildConfig.DEBUG) Log.d(TAG, "navigateObserver: received directions to ${resources.getResourceEntryName(resId)}")

        findNavController().navigate(resId)
    }
