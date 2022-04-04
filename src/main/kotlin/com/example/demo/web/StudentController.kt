package com.example.demo.web

import com.example.demo.application.ListStudentsUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StudentController(
  private val listStudentsUseCase: ListStudentsUseCase,
) {

  @GetMapping("/students")
  fun listStudents(request: ListStudentRequest): ListStudentResponse {
    val students = listStudentsUseCase.handle(request.toQuery())
    return ListStudentResponse(students, students.size)
  }
}
