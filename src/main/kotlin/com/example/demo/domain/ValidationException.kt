package com.example.demo.domain

class ValidationException(override val message: String?, override val cause: Throwable? = null): RuntimeException()
