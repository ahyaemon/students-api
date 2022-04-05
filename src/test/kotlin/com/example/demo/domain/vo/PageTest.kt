package com.example.demo.domain.vo

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class PageTest {

  @Test
  fun `数値を渡すと正しく作成される`() {
    Page.of("3") shouldBe Page(3)
  }

  @Test
  fun `nullを渡すとデフォルト値で返ってくる`() {
    Page.of(null) shouldBe Page(1)
  }

  @Test
  fun `文字列を渡すとエラー`() {
    shouldThrowExactly<ValidationException> {
      Page.of("a")
    }
  }
}
