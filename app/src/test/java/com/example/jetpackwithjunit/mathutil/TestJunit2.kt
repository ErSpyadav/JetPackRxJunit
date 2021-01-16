package com.example.jetpackwithjunit.mathutil

import org.junit.Assert
import org.junit.Test

open class TestJunit2 {
    var message = "Robert"
    var messageUtil = MessageUtil(message)
    @Test
    fun testSalutationMessage() {
        println("Inside testSalutationMessage()")
        message = "Hi!" + "Robert"
        Assert.assertEquals(message, messageUtil.salutationMessage())
    }
}