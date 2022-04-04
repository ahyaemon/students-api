package com.example.demo.web

import com.example.demo.application.ListStudentQuery

data class ListStudentRequest(
  val teacherId: Int,
) {

  fun toQuery(): ListStudentQuery = ListStudentQuery(
    teacherId = teacherId,
  )
}
