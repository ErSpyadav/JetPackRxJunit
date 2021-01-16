package com.example.jetpackwithjunit.mathutil

import org.junit.Assert.assertEquals
import org.junit.Test

open class TestJunit1 {
    var message = "Robert"
    var messageUtil = MessageUtil(message)
    @Test
    fun testPrintMessage() {
        println("Inside testPrintMessage()")
        assertEquals(message, messageUtil.printMessage())
    }
}