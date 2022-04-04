package com.example.demo.domain.vo

data class Pager(
  val page: Page,
  val limit: Limit,
) {

  val offset: Int
  get() = (page.value - 1) * limit.value

  companion object {

    fun of(p: String?, l: String?): Pager {
      return Pager(Page.of(p), Limit.of(l))
    }
  }
}
