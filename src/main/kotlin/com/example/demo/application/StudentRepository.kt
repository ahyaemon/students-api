package com.example.demo.application

import com.example.demo.application.data.Student
import com.example.demo.domain.vo.*

interface StudentRepository {

  fun listByTeacherId(
    teacherId: TeacherId,
    pager: Pager,
    sorter: Sorter,
    nameLike: NameLike,
    loginIdLike: LoginIdLike,
  ): List<Student>
}
