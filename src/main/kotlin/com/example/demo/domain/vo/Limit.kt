package com.example.demo.domain.vo

data class Limit(
  val value: Int,
) {

  companion object {

    private const val DEFAULT_LIMIT = 10

    fun of(v: String?): Limit {
      if (v == null) {
        return Limit(DEFAULT_LIMIT)
      }

      return try {
        Limit(v.toInt())
      } catch (e: Exception) {
        throw ValidationException("limitには数値を入力してください")
      }
    }
  }
}
