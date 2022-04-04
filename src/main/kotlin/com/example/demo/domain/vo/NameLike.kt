package com.example.demo.domain.vo

data class NameLike(
  val value: String?
) {

  companion object {

    fun of(v: String?): NameLike {
      return NameLike(v)
    }
  }
}
