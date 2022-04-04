package com.example.demo.application

import com.example.demo.domain.*

data class ListStudentQuery(
  val teacherId: TeacherId,
  val pager: Pager,
  val sorter: Sorter,
  val nameLike: NameLike,
  val loginIdLike: LoginIdLike,
)
