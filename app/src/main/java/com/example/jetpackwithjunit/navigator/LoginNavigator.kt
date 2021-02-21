package com.example.jetpackwithjunit.navigator

interface LoginNavigator {
    fun signUpClick()
    fun mobileInLineError(isEnabled: Boolean)
    fun passwordInLine(isEnabled: Boolean)
}