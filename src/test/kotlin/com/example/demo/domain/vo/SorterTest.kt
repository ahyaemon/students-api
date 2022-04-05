package com.example.demo.domain.vo

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class SorterTest {

  @Test
  fun `値を渡すと正しく作成される`() {
    Sorter.of("name", "asc") shouldBe Sorter(SortKey.NAME, Order.ASC)
  }
}
