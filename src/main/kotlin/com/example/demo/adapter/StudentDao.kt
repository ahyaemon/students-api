package com.example.demo.adapter

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface StudentDao: JpaRepository<StudentRecord, Int> {
  @Query("""
    select 
        s.id, 
        s.name,
        s.login_id,
        c.id as classroom_id,
        c.name as classroom_name
    from 
        student s
    inner join
        student_belong_classroom sbc
        on
            sbc.student_id = s.id
    inner join
        classroom c
        on
            c.id = sbc.classroom_id
    inner join
        teacher_belong_classroom tbc
        on
            tbc.classroom_id = c.id
            and tbc.teacher_id = :teacherId
    """, nativeQuery = true)
  fun list(@Param("teacherId") teacherId: Int): List<StudentRecord>
}
