package com.example.demo.web

import com.example.demo.application.ListStudentsUseCase
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
internal class StudentControllerTest {

  @Autowired
  private lateinit var mockMvc: MockMvc

  @MockkBean
  private lateinit var listStudentsUseCase: ListStudentsUseCase

  @Test
  fun `200`() {
    every { listStudentsUseCase.handle(any()) } returns emptyList()

    mockMvc
      .perform(
        MockMvcRequestBuilders.get("/students").queryParam("teacherId", "1")
      )
      .andExpect(MockMvcResultMatchers.status().isOk)
  }

  @Test
  fun `400`() {
    mockMvc
      .perform(
        MockMvcRequestBuilders.get("/students")
      )
      .andExpect(MockMvcResultMatchers.status().isBadRequest)
  }
}
