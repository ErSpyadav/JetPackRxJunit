package com.example.jetpackwithjunit.dagger

import android.util.Log
import javax.inject.Inject

class Car @Inject constructor(private val engine: Engine, private val wheels: Wheels) {
    fun drive() {
        Log.d(TAG, "driving...... ")
    }
    fun carDetail(){
        print("Car detail")
        println(engine.engineName())
        println(wheels.wheelName())
    }

    companion object {
        private const val TAG = "Car"
    }
}