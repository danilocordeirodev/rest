package com.oriedroc.rest.application.core.usecase

import com.oriedroc.rest.application.core.domain.Person
import com.oriedroc.rest.application.core.exception.ResourceNotFoundException
import com.oriedroc.rest.application.ports.`in`.UpdatePersonInputPort
import com.oriedroc.rest.application.ports.out.FindPersonOutputPort
import com.oriedroc.rest.application.ports.out.UpdatePersonOutputPort
import org.springframework.stereotype.Service
import java.util.*
import java.util.logging.Logger

@Service
class UpdatePersonUseCase(
    private val updatePersonOutputPort: UpdatePersonOutputPort,
    private val findPersonOutputPort: FindPersonOutputPort
) : UpdatePersonInputPort {
    private val logger = Logger.getLogger(UpdatePersonUseCase::class.java.name)
    override fun update(person: Person, uuid: UUID): Person {
        logger.info("Updating one person with UUID ${uuid}!")

        val personFound = findPersonOutputPort.findByUuid(uuid)
            ?: throw ResourceNotFoundException("No records found for this UUID!");

        personFound.firstName = person.firstName
        personFound.lastName = person.lastName
        personFound.gender = person.gender
        return updatePersonOutputPort.update(personFound);
    }
}