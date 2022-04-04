package com.example.demo.domain

enum class Order {
  ASC,
  DESC,
  ;

  companion object {

    private val DEFAULT_ORDER = ASC

    fun of(v: String?): Order {
      return when(v) {
        null -> DEFAULT_ORDER
        "asc" -> ASC
        "desc" -> DESC
        else -> throw ValidationException("orderには'asc'か'desc'を指定してください")
      }
    }
  }
}
