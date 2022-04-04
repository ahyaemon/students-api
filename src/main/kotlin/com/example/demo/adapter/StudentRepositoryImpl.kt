package com.example.demo.adapter

import com.example.demo.application.StudentRepository
import com.example.demo.application.data.Classroom
import com.example.demo.application.data.Student
import com.example.demo.domain.vo.*
import org.springframework.stereotype.Repository

@Repository
class StudentRepositoryImpl(
  private val studentDao: StudentDao,
): StudentRepository {

  override fun listByTeacherId(
    teacherId: TeacherId,
    pager: Pager,
    sorter: Sorter,
    nameLike: NameLike,
    loginIdLike: LoginIdLike
  ): List<Student> {
    return studentDao
      .list(teacherId = teacherId.value)
      .groupBy { it.id }
      .map { (id, records) ->
        val classrooms = records.map { record ->
          Classroom(id = record.classroomId, name = record.classroomName)
        }

        Student(
          id = id,
          name = records.first().name,
          loginId = records.first().loginId,
          classrooms = classrooms,
        )
      }
  }
}

