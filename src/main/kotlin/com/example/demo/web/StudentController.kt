package com.example.demo.web

import com.example.demo.application.ListStudentsUseCase
import com.example.demo.domain.vo.ValidationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StudentController(
  private val listStudentsUseCase: ListStudentsUseCase,
) {

  @GetMapping("/students")
  fun listStudents(request: ListStudentRequest): ListStudentResponse {
    println(request)
    val students = listStudentsUseCase.handle(request.toQuery())
    return ListStudentResponse(students, students.size)
  }

  @ExceptionHandler(ValidationException::class)
  fun handleValidationException(e: ValidationException): String {
    return e.message ?: "不明なエラーが発生しました"
  }
}
