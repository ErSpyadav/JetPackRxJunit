package com.example.jetpackwithjunit.mathutil
//Constructor @param message to be printed
class MessageUtil
    (private var message: String) {
    // prints the message
    fun printMessage(): String {
        println(message)
        return message
    }

    // add "Hi!" to the message
    fun salutationMessage(): String {
        message = "Hi!$message"
        println(message)
        return message
    }
}