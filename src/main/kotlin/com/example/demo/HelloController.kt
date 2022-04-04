package com.example.demo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@RestController
class HelloController(
	private val dogRepository: DogRepository,
) {

	@GetMapping
	@Transactional
	fun hello(): List<Dog> {
		return dogRepository.findAll().toList()
	}
}

@Entity
@Table(name = "dog")
class Dog(
	@Id
	@Column(name = "id")
	var id: Int,

	@Column(name = "name")
	var name: String,
)

@Repository
interface DogRepository: CrudRepository<Dog, Int>
