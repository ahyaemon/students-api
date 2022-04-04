package com.example.demo.domain.vo

data class NameLike(
  val value: String?
) {

  val isNotEmpty: Boolean
  get() = value != null

  companion object {

    fun of(v: String?): NameLike {
      return NameLike(v)
    }
  }
}
