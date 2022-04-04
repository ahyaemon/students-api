package com.example.demo.adapter

import com.example.demo.application.StudentRepository
import com.example.demo.application.data.Classroom
import com.example.demo.application.data.Student
import com.example.demo.domain.vo.*
import com.example.demo.jooq.tables.references.CLASSROOM
import com.example.demo.jooq.tables.references.STUDENT
import com.example.demo.jooq.tables.references.STUDENT_BELONG_CLASSROOM
import com.example.demo.jooq.tables.references.TEACHER_BELONG_CLASSROOM
import org.jooq.DSLContext
import org.jooq.impl.DSL.noCondition
import org.springframework.stereotype.Repository

@Repository
class StudentRepositoryImpl(
  private val dslContext: DSLContext,
): StudentRepository {

  override fun listByTeacherId(
    teacherId: TeacherId,
    pager: Pager,
    sorter: Sorter,
    nameLike: NameLike,
    loginIdLike: LoginIdLike
  ): List<Student> {
    val conditionedStudentTable = dslContext
      .selectDistinct(
        STUDENT.ID,
        STUDENT.NAME,
        STUDENT.LOGIN_ID,
      )
      .from(STUDENT)
      .innerJoin(STUDENT_BELONG_CLASSROOM)
      .on(STUDENT_BELONG_CLASSROOM.STUDENT_ID.eq(STUDENT.ID))
      .innerJoin(CLASSROOM)
      .on(CLASSROOM.ID.eq(STUDENT_BELONG_CLASSROOM.CLASSROOM_ID))
      .innerJoin(TEACHER_BELONG_CLASSROOM)
      .on(TEACHER_BELONG_CLASSROOM.CLASSROOM_ID.eq(CLASSROOM.ID))
      .and(TEACHER_BELONG_CLASSROOM.TEACHER_ID.eq(1))
      .where(createLike(nameLike, loginIdLike))
      .orderBy(sorter.createOrderBy())
      .limit(pager.limit.value)
      .offset(pager.offset)

    return dslContext
      .select(
        conditionedStudentTable.field(STUDENT.ID),
        conditionedStudentTable.field(STUDENT.NAME),
        conditionedStudentTable.field(STUDENT.LOGIN_ID),
        CLASSROOM.ID.`as`("classroom_id"),
        CLASSROOM.NAME.`as`("classroom_name"),
      )
      .from(conditionedStudentTable)
      .innerJoin(STUDENT_BELONG_CLASSROOM)
      .on(STUDENT_BELONG_CLASSROOM.STUDENT_ID.eq(conditionedStudentTable.field(STUDENT.ID)))
      .innerJoin(CLASSROOM)
      .on(CLASSROOM.ID.eq(STUDENT_BELONG_CLASSROOM.CLASSROOM_ID))
      .fetchInto(StudentRecord::class.java)
      .groupBy { it.id }
      .map { it.toStudent() }
  }

  private fun Map.Entry<Int, List<StudentRecord>>.toStudent(): Student =
    Student(
      id = key,
      name = value.first().name,
      loginId = value.first().loginId,
      classrooms = value.map { record ->
        Classroom(id = record.classroomId, name = record.classroomName)
      },
    )


  private fun createLike(
    nameLike: NameLike,
    loginIdLike: LoginIdLike
  ) = noCondition()
    .run {
      if (nameLike.isNotEmpty) {
        and(STUDENT.NAME.like("%${nameLike.value}%"))
      } else {
        and(noCondition())
      }
    }
    .run {
      if (loginIdLike.isNotEmpty) {
        and(STUDENT.LOGIN_ID.like("%${loginIdLike.value}%"))
      } else {
        and(noCondition())
      }
    }

  private fun Sorter.createOrderBy() = when (sortKey) {
    SortKey.NAME -> STUDENT.NAME
    SortKey.LOGIN_ID -> STUDENT.LOGIN_ID
  }.apply {
    when (order) {
      Order.ASC -> asc()
      Order.DESC -> desc()
    }
  }

  data class StudentRecord(
    val id: Int,
    val name: String,
    val loginId: String,
    val classroomId: Int,
    val classroomName: String,
  )
}
