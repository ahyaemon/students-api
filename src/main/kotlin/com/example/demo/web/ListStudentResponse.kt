package com.example.demo.web

import com.example.demo.application.data.Student

data class ListStudentResponse(
  val students: List<Student>,
  val totalCount: Int,
)
