package com.example.demo.web

import com.example.demo.application.ListStudentQuery
import com.example.demo.domain.vo.*

data class ListStudentsRequest(
  val teacherId: String?,

  // 表示するページ番号を指定します
  val page: String?,

  // ページに表示するデータ数を指定します
  val limit: String?,

  // ソートキーを指定します。（名前: name | ログインID: loginId）
  val sort: String?,

  // 昇順・昇順を指定します。（降順: desc | 昇順: asc）
  val order: String?,

  // name属性による部分一致検索。
  val name_like: String?,

  // loginId属性による部分一致検索。
  val loginId_like: String?,
) {

  fun toQuery(): ListStudentQuery = ListStudentQuery(
    teacherId = TeacherId.of(teacherId),
    pager = Pager.of(page, limit),
    sorter = Sorter.of(sort, order),
    nameLike = NameLike.of(name_like),
    loginIdLike = LoginIdLike.of(loginId_like)
  )
}
