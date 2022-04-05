package com.example.demo.domain.vo

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class OrderTest {

  @Test
  fun `"asc"を渡すとASCが返ってくる`() {
    Order.of("asc") shouldBe Order.ASC
  }

  @Test
  fun `"desc"を渡すとDESCが返ってくる`() {
    Order.of("desc") shouldBe Order.DESC
  }

  @Test
  fun `存在しない値を渡すとエラー`() {
    shouldThrowExactly<ValidationException> {
      Order.of("a")
    }
  }
}
