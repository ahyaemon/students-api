package com.example.demo.domain.vo

enum class SortKey(val value: String) {
  NAME("name"),
  LOGIN_ID("login_id"),
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
