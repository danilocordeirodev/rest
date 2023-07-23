package com.oriedroc.rest.adapters.out.persistence

import com.oriedroc.rest.adapters.out.persistence.mapper.PersonEntityMapper
import com.oriedroc.rest.adapters.out.persistence.repository.PersonRepository
import com.oriedroc.rest.application.core.domain.Person
import com.oriedroc.rest.application.ports.out.CreatePersonOutputPort
import com.oriedroc.rest.application.ports.out.DeletePersonOutputPort
import com.oriedroc.rest.application.ports.out.FindPersonOutputPort
import com.oriedroc.rest.application.ports.out.UpdatePersonOutputPort
import org.springframework.stereotype.Component
import java.util.*

@Component
class PersonJpaAdapter(
    private val personRepository: PersonRepository,
    private val personEntityMapper: PersonEntityMapper
) : CreatePersonOutputPort, FindPersonOutputPort, UpdatePersonOutputPort, DeletePersonOutputPort{
    override fun create(person: Person): Person =
        personRepository.save(
            personEntityMapper.toEntity(person)
        ).let { personEntityMapper.toDomain(it) }

    override fun update(person: Person): Person =
        personRepository.saveAndFlush(
            personEntityMapper.toEntity(person)
        ).let { personEntityMapper.toDomain(it) }

    override fun findByUuid(uuid: UUID): Person? =
        personRepository.findByUuid(uuid)?.let { personEntityMapper.toDomain(it) }

    override fun findAll(): List<Person> =
        personRepository.findAll().let {
            personEntities -> personEntities.map { personEntityMapper.toDomain(it) }
        }
    override fun delete(person: Person) {
        personRepository.delete(personEntityMapper.toEntity(person));
    }
}