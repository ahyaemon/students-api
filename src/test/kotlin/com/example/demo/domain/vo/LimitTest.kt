package com.example.demo.domain.vo

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class LimitTest {

  @Test
  fun `数値を渡すと正しく作成される`() {
    Limit.of("3") shouldBe Limit(3)
  }

  @Test
  fun `null を渡すとデフォルト値で返ってくる`() {
    Limit.of(null) shouldBe Limit(10)
  }

  @Test
  fun `文字列を渡すとエラー`() {
    shouldThrowExactly<ValidationException> {
      Limit.of("a")
    }
  }
}
