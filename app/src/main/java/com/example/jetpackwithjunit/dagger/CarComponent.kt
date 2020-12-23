package com.example.jetpackwithjunit.dagger

import dagger.Component

@Component
interface CarComponent {
    val car: Car?
}