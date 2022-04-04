package com.example.demo.adapter

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class StudentRecord(
  @Id
  var id: Int,
  var name: String,
  var loginId: String,
  var classroomId: Int,
  var classroomName: String,
)
