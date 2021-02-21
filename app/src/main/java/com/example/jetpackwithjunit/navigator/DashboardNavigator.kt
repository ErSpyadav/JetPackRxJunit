package com.example.jetpackwithjunit.navigator

interface DashboardNavigator {
    fun signUpClick()
    fun mobileInLineError(isEnabled: Boolean)
    fun passwordInLine(isEnabled: Boolean)
}