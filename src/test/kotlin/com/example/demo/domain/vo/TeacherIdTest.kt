package com.example.demo.domain.vo

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class TeacherIdTest {

  @Test
  fun `数値を渡すと正しく作成される`() {
    TeacherId.of("1") shouldBe TeacherId(1)
  }

  @Test
  fun `nullを渡すとエラーが返ってくる`() {
    shouldThrowExactly<ValidationException> {
      TeacherId.of(null)
    }
  }

  @Test
  fun `文字列を渡すとエラーが返ってくる`() {
    shouldThrowExactly<ValidationException> {
      TeacherId.of("a")
    }
  }
}


