package com.example.demo.domain.vo

data class LoginIdLike(
  val value: String?
) {

  val isNotEmpty: Boolean
  get() = value != null

  companion object {

    fun of(v: String?): LoginIdLike {
      return LoginIdLike(v)
    }
  }
}
