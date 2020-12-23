package com.example.jetpackwithjunit.dagger

import android.util.Log
import javax.inject.Inject

class Wheels @Inject constructor(){
    fun wheelName(){
        Log.d(TAG, "wheelName: Wheer")
    }
    companion object{
      private const val TAG ="Wheel"
    }
}