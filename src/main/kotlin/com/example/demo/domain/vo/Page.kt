package com.example.demo.domain.vo

data class Page(
  val value: Int
) {

  companion object {

    private const val DEFAULT_PAGE_NUMBER = 1

    fun of(v: String?): Page {
      if (v == null) {
        return Page(DEFAULT_PAGE_NUMBER)
      }

      return try {
        Page(v.toInt())
      } catch (e: Exception) {
        throw ValidationException("pageには数値を入力してください", e)
      }
    }
  }
}
