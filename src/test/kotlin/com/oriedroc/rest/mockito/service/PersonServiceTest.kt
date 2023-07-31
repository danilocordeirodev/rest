package com.oriedroc.rest.mockito.service

import com.oriedroc.rest.exceptions.ResourceNotFoundException
import com.oriedroc.rest.repository.PersonRepository
import com.oriedroc.rest.service.PersonService
import com.oriedroc.rest.unittests.mapper.MockPerson
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class PersonServiceTest {

    private lateinit var inputObject: MockPerson

    @InjectMocks
    private lateinit var service: PersonService

    @Mock
    private lateinit var repository: PersonRepository
    @BeforeEach
    fun setUp() {
        inputObject = MockPerson()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun findAll() {
        val persons =  inputObject.mockEntityList()

        `when`(repository.findAll()).thenReturn(persons)

        val result = service.findAll()
        assertNotNull(result)
        assertEquals(14, result.size)

    }

    @Test
    fun findById() {
        val person = inputObject.mockEntity(1)
        person.id = 1

        `when`(repository.findById(1)).thenReturn(Optional.of(person))
        val result =  service.findById(1)

        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)

        assertTrue(result.links.toString().contains("/person/1"))
    }

    @Test
    fun create() {

        val entity = inputObject.mockEntity(1)

        val persisted =  entity.copy()
        persisted.id = 1

        val vo = inputObject.mockVO(1)

        `when`(repository.save(entity)).thenReturn(persisted)

        val result =  service.create(vo)

        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)

        assertTrue(result.links.toString().contains("/person/1"))


    }

    @Test
    fun update() {

        val entity = inputObject.mockEntity(1)

        val persisted =  entity.copy()
        persisted.id = 1

        val vo = inputObject.mockVO(1)

        `when`(repository.findById(1)).thenReturn(Optional.of(entity))
        `when`(repository.save(entity)).thenReturn(persisted)

        val result =  service.update(vo)

        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)

        assertTrue(result.links.toString().contains("/person/1"))
    }

    @Test
    fun shouldThrowExceptionWhenNotFindPersonByID() {

        val vo = inputObject.mockVO()


        `when`(repository.findById(0)).thenThrow(ResourceNotFoundException("No records found for this ID!"))

        val exception: Exception = assertThrows(
            ResourceNotFoundException::class.java
        ) {
            service.update(vo)
        }

        val expectedMessage = "No records found for this ID!"
        val actualMessage =  exception.message

        assertTrue(actualMessage!!.contains(expectedMessage))
    }

    @Test
    fun delete() {
    }
}