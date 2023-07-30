package com.oriedroc.rest.service

import com.oriedroc.rest.controller.PersonController
import com.oriedroc.rest.data.vo.v1.PersonVO
import com.oriedroc.rest.exceptions.ResourceNotFoundException
import com.oriedroc.rest.mapper.DozerMapper
import com.oriedroc.rest.model.Person
import com.oriedroc.rest.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {
    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<PersonVO> {
        logger.info("Finding all people!")
        val vos =  DozerMapper.parseListObjects(repository.findAll(), PersonVO::class.java)
        for (person in vos) {
            val withSelfRel = linkTo(PersonController::class.java).slash(person.key).withSelfRel()
            person.add(withSelfRel)
        }

        return vos

    }

    fun findById(id: Long): PersonVO {
        logger.info("Finding one person!")
        val person = repository.findById(id)
                .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        val personVO : PersonVO = DozerMapper.parseObject(person, PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun create(person: PersonVO) : PersonVO{
        logger.info("Creating one person with name ${person.firstName}!")
        val entity: Person = DozerMapper.parseObject(person, Person::class.java)
        val personVO : PersonVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)

        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun update(person: PersonVO) : PersonVO{
        logger.info("Updating one person with ID ${person.key}!")
        val entity = repository.findById(person.key)
                .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.gender = person.gender
        val personVO : PersonVO =  DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun delete(id: Long) {
        logger.info("Deleting one person with ID $id!")
        val entity = repository.findById(id)
                .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        repository.delete(entity)
    }
}