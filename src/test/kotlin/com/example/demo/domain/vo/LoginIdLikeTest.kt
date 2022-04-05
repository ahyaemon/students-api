package com.example.demo.domain.vo

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class LoginIdLikeTest {

  @Test
  fun `値があるときはisNotEmptyがtrue`() {
    LoginIdLike.of("a").isNotEmpty shouldBe true
  }

  @Test
  fun `値がnullのときはisNotEmptyがfalse`() {
    LoginIdLike.of(null).isNotEmpty shouldBe false
  }
}
