package com.example.demo.domain.vo

class ValidationException(override val message: String?, override val cause: Throwable? = null): RuntimeException()
