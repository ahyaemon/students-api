package com.example.demo.web

import com.example.demo.application.data.Student

data class ListStudentsResponse(
  val students: List<Student>,
  val totalCount: Int,
)
