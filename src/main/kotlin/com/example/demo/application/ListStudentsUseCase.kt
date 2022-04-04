package com.example.demo.application

import com.example.demo.application.data.Student

interface ListStudentsUseCase {

  fun handle(query: ListStudentQuery): List<Student>
}
