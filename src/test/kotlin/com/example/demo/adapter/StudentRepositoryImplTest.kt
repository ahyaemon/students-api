package com.example.demo.adapter

import com.example.demo.application.data.Classroom
import com.example.demo.application.data.Student
import com.example.demo.domain.vo.*
import com.example.demo.jooq.tables.records.*
import com.example.demo.jooq.tables.references.*
import io.kotest.matchers.shouldBe
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal class StudentRepositoryImplTest {

  @Autowired
  private lateinit var studentRepositoryImpl: StudentRepositoryImpl

  @Autowired
  private lateinit var dslContext: DSLContext

  @BeforeEach
  fun setUp() {
    // データクリア
    dslContext.execute("SET REFERENTIAL_INTEGRITY FALSE")
    dslContext.truncate(STUDENT_BELONG_CLASSROOM).execute()
    dslContext.truncate(TEACHER_BELONG_CLASSROOM).execute()
    dslContext.truncate(STUDENT).execute()
    dslContext.truncate(CLASSROOM).execute()
    dslContext.truncate(TEACHER).execute()

    // データ挿入
    dslContext.insertClassroom(1, "classA")
    dslContext.insertClassroom(2, "classB")
    dslContext.insertStudent(1, "taro", "loginId11", listOf(1))
    dslContext.insertStudent(2, "jiro", "loginId12", listOf(1, 2))
    dslContext.insertStudent(3, "saburo", "loginId22", listOf(2))
    dslContext.insertTeacher(1, listOf(1))
    dslContext.insertTeacher(2, listOf(1, 2))
  }

  @AfterEach
  fun tearDown() {
    dslContext.execute("SET REFERENTIAL_INTEGRITY TRUE")
  }

  @Test
  fun `単一クラスをもつ先生のIDで取得`() {
    val students = studentRepositoryImpl.listByTeacherId(
      teacherId = TeacherId(1),
      pager = Pager(Page(1), Limit(100)),
      sorter = Sorter(SortKey.LOGIN_ID, Order.ASC),
      nameLike = NameLike(null),
      loginIdLike = LoginIdLike(null),
    )

    students shouldBe listOf(
      Student(1, "taro", "loginId11", listOf(
        Classroom(1, "classA"),
      )),
      Student(2, "jiro", "loginId12", listOf(
        Classroom(1, "classA"),
        Classroom(2, "classB"),
      )),
    )
  }

  @Test
  fun `複数クラスをもつ先生のIDで取得`() {
    val students = studentRepositoryImpl.listByTeacherId(
      teacherId = TeacherId(2),
      pager = Pager(Page(1), Limit(100)),
      sorter = Sorter(SortKey.LOGIN_ID, Order.ASC),
      nameLike = NameLike(null),
      loginIdLike = LoginIdLike(null),
    )

    students shouldBe listOf(
      Student(1, "taro", "loginId11", listOf(
        Classroom(1, "classA"),
      )),
      Student(2, "jiro", "loginId12", listOf(
        Classroom(1, "classA"),
        Classroom(2, "classB"),
      )),
      Student(3, "saburo", "loginId22", listOf(
        Classroom(2, "classB"),
      )),
    )
  }

  @Test
  fun `nameLikeでフィルター`() {
    val students = studentRepositoryImpl.listByTeacherId(
      teacherId = TeacherId(2),
      pager = Pager(Page(1), Limit(100)),
      sorter = Sorter(SortKey.LOGIN_ID, Order.ASC),
      nameLike = NameLike("a"),
      loginIdLike = LoginIdLike(null),
    )

    students shouldBe listOf(
      Student(1, "taro", "loginId11", listOf(
        Classroom(1, "classA"),
      )),
      Student(3, "saburo", "loginId22", listOf(
        Classroom(2, "classB"),
      )),
    )
  }

  @Test
  fun `loginIdLikeでフィルター`() {
    val students = studentRepositoryImpl.listByTeacherId(
      teacherId = TeacherId(2),
      pager = Pager(Page(1), Limit(100)),
      sorter = Sorter(SortKey.LOGIN_ID, Order.ASC),
      nameLike = NameLike(null),
      loginIdLike = LoginIdLike("2"),
    )

    students shouldBe listOf(
      Student(2, "jiro", "loginId12", listOf(
        Classroom(1, "classA"),
        Classroom(2, "classB"),
      )),
      Student(3, "saburo", "loginId22", listOf(
        Classroom(2, "classB"),
      )),
    )
  }

  @Test
  fun `limitで件数制限`() {
    val students = studentRepositoryImpl.listByTeacherId(
      teacherId = TeacherId(2),
      pager = Pager(Page(1), Limit(2)),
      sorter = Sorter(SortKey.LOGIN_ID, Order.ASC),
      nameLike = NameLike(null),
      loginIdLike = LoginIdLike(null),
    )

    students shouldBe listOf(
      Student(1, "taro", "loginId11", listOf(
        Classroom(1, "classA"),
      )),
      Student(2, "jiro", "loginId12", listOf(
        Classroom(1, "classA"),
        Classroom(2, "classB"),
      )),
    )
  }

  @Test
  fun `pageでページ指定`() {
    val students = studentRepositoryImpl.listByTeacherId(
      teacherId = TeacherId(2),
      pager = Pager(Page(2), Limit(2)),
      sorter = Sorter(SortKey.LOGIN_ID, Order.ASC),
      nameLike = NameLike(null),
      loginIdLike = LoginIdLike(null),
    )

    students shouldBe listOf(
      Student(3, "saburo", "loginId22", listOf(
        Classroom(2, "classB"),
      )),
    )
  }

  @Test
  fun `名前順に並べ替え`() {
    val students = studentRepositoryImpl.listByTeacherId(
      teacherId = TeacherId(2),
      pager = Pager(Page(1), Limit(100)),
      sorter = Sorter(SortKey.NAME, Order.DESC),
      nameLike = NameLike(null),
      loginIdLike = LoginIdLike(null),
    )

    students shouldBe listOf(
      Student(1, "taro", "loginId11", listOf(
        Classroom(1, "classA"),
      )),
      Student(3, "saburo", "loginId22", listOf(
        Classroom(2, "classB"),
      )),
      Student(2, "jiro", "loginId12", listOf(
        Classroom(1, "classA"),
        Classroom(2, "classB"),
      )),
    )
  }

  private fun DSLContext.insertClassroom(id: Int, name: String) =
    insertInto(CLASSROOM)
      .set(ClassroomRecord(id, name))
      .execute()

  private fun DSLContext.insertStudent(id: Int, name: String, loginId: String, classroomIds: List<Int>) {
    insertInto(STUDENT)
      .set(StudentRecord(id, loginId, name))
      .execute()

    batch(
      classroomIds.map {
        insertInto(STUDENT_BELONG_CLASSROOM)
          .set(StudentBelongClassroomRecord(id, it))
      }
    ).execute()
  }

  private fun DSLContext.insertTeacher(id: Int, classroomIds: List<Int>) {
    insertInto(TEACHER)
      .set(TeacherRecord(id))
      .execute()

    batch(
      classroomIds.map {
        insertInto(TEACHER_BELONG_CLASSROOM)
          .set(TeacherBelongClassroomRecord(id, it))
      }
    ).execute()
  }
}
