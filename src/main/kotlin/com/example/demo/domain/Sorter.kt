package com.example.demo.domain

data class Sorter(
  val sortKey: SortKey,
  val order: Order,
) {

  companion object {

    fun of(s: String?, o: String?): Sorter {
      return Sorter(SortKey.of(s), Order.of(o))
    }
  }
}
