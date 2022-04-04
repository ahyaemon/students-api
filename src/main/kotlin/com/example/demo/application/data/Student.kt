package com.example.demo.application.data

data class Student(
  val id: Int,
  val name: String,
  val loginId: String,
  val classRooms: List<Classroom>
)
