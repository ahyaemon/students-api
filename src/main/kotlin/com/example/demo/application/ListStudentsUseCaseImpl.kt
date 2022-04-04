package com.example.demo.application

import com.example.demo.application.data.Classroom
import com.example.demo.application.data.Student
import org.springframework.stereotype.Component

@Component
class ListStudentsUseCaseImpl: ListStudentsUseCase {

  override fun handle(query: ListStudentQuery): List<Student> {
    return listOf(
      Student(1, "taro", "student_1", listOf(
        Classroom(1, "class1"),
        Classroom(2, "class2"),
      )),
      Student(2, "jiro", "student_2", listOf(
        Classroom(1, "class1"),
      )),
    )
  }
}
