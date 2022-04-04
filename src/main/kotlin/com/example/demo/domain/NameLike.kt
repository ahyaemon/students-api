package com.example.demo.domain

data class NameLike(
  val value: String?
) {

  companion object {

    fun of(v: String?): NameLike {
      return NameLike(v)
    }
  }
}
