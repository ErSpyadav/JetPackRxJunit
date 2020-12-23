package com.example.jetpackwithjunit.dagger

import android.util.Log
import javax.inject.Inject

class Engine @Inject constructor(){

    fun engineName(){
        Log.d(Companion.TAG, "engineName:Mahindra ")
    }

    companion object {
        private const val TAG = "Engine"
    }
}