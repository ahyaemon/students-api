package com.example.demo.domain

data class TeacherId(
  val value: Int
) {

  companion object {

    fun of(v: String?): TeacherId {
      if (v == null) {
        throw ValidationException("teacherIdは必須です")
      }

      return try {
        TeacherId(v.toInt())
      } catch (e: Exception) {
        throw ValidationException("teacherIdには数値を入力してください")
      }
    }
  }
}
