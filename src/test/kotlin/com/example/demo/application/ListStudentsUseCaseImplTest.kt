package com.example.demo.application

import com.example.demo.application.data.Classroom
import com.example.demo.application.data.Student
import com.example.demo.domain.vo.*
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class ListStudentsUseCaseImplTest {

  @Test
  fun handle() {
    val students = listOf(
      Student(1, "taro", "loginId1", listOf(
        Classroom(1, "class1"),
        Classroom(2, "class2"),
      )),
      Student(2, "jiro", "loginId2", listOf(
        Classroom(1, "class1"),
      )),
    )

    val repository = mockk<StudentRepository>().apply {
      every { listByTeacherId(any(), any(), any(), any(), any()) } returns students
    }

    val useCase = ListStudentsUseCaseImpl(repository)
    val query = ListStudentQuery(
      TeacherId(1),
      Pager(Page(1), Limit(10)),
      Sorter(SortKey.NAME, Order.ASC),
      NameLike(null),
      LoginIdLike(null),
    )
    val result = useCase.handle(query)

    result shouldBe students
  }
}
