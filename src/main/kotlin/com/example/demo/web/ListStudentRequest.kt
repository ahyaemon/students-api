package com.example.demo.web

import com.example.demo.application.ListStudentQuery
import com.example.demo.domain.TeacherId

data class ListStudentRequest(
  val teacherId: String?,
) {

  fun toQuery(): ListStudentQuery = ListStudentQuery(
    teacherId = TeacherId.of(teacherId),
  )
}
