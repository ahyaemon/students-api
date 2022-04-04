package com.example.demo.application

import com.example.demo.application.data.Student
import org.springframework.stereotype.Component

@Component
class ListStudentsUseCaseImpl(
  private val studentRepository: StudentRepository,
): ListStudentsUseCase {

  override fun handle(query: ListStudentQuery): List<Student> {
    return studentRepository.listByTeacherId(
      teacherId = query.teacherId,
      pager = query.pager,
      sorter = query.sorter,
      nameLike = query.nameLike,
      loginIdLike = query.loginIdLike,
    )
  }
}
