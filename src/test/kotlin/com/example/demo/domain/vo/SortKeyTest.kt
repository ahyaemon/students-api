package com.example.demo.domain.vo

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class SortKeyTest {

  @Test
  fun `nameを渡すとNAMEが返ってくる`() {
    SortKey.of("name") shouldBe SortKey.NAME
  }

  @Test
  fun `loginIdを渡すとLOGIN_IDが返ってくる`() {
    SortKey.of("loginId") shouldBe SortKey.LOGIN_ID
  }

  @Test
  fun `存在しない値を渡すとエラー`() {
    shouldThrowExactly<ValidationException> {
      SortKey.of("a")
    }
  }
}
