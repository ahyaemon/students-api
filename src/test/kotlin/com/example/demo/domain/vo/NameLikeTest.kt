package com.example.demo.domain.vo

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class NameLikeTest {

  @Test
  fun `値があるときはisNotEmptyがtrue`() {
    NameLike.of("a").isNotEmpty shouldBe true
  }

  @Test
  fun `値がnullのときはisNotEmptyがfalse`() {
    NameLike.of(null).isNotEmpty shouldBe false
  }
}
