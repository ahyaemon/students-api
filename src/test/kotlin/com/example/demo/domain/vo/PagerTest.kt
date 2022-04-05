package com.example.demo.domain.vo

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class PagerTest {

  @Test
  fun `offsetが正しく取得できる`() {
    Pager.of("1", "10").offset shouldBe 0
    Pager.of("2", "10").offset shouldBe 10

    Pager.of("1", "5").offset shouldBe 0
    Pager.of("2", "5").offset shouldBe 5
  }
}
