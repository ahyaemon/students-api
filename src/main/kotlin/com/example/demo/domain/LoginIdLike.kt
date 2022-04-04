package com.example.demo.domain

data class LoginIdLike(
  val value: String?
) {

  companion object {

    fun of(v: String?): LoginIdLike {
      return LoginIdLike(v)
    }
  }
}
