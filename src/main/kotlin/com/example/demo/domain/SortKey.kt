package com.example.demo.domain

enum class SortKey {
  NAME,
  LOGIN_ID,
  ;

  companion object {

    private val DEFAULT_SORT_KEY = NAME

    fun of(v: String?): SortKey {
      return when (v) {
        null -> DEFAULT_SORT_KEY
        "name" -> NAME
        "loginId" -> LOGIN_ID
        else -> throw ValidationException("sortは'name'か'loginId'を指定してください")
      }
    }
  }
}
