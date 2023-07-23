package com.oriedroc.rest.application.core.usecase

import com.oriedroc.rest.application.core.domain.Person
import com.oriedroc.rest.application.core.exception.ResourceNotFoundException
import com.oriedroc.rest.application.ports.`in`.FindPersonInputPort
import com.oriedroc.rest.application.ports.out.FindPersonOutputPort
import org.springframework.stereotype.Service
import java.util.*
import java.util.logging.Logger

@Service
class FindPersonUseCase(
    private val findPersonOutputPort: FindPersonOutputPort
) : FindPersonInputPort {

    private val logger = Logger.getLogger(FindPersonUseCase::class.java.name)
    override fun findByUuid(personUuid: UUID): Person {
        logger.info("Finding one person!")
        return findPersonOutputPort.findByUuid(personUuid)
            ?: throw ResourceNotFoundException("No records found for this UUID: ${personUuid}!");
    }

    override fun findAll(): List<Person> {
        logger.info("Finding all people!")
        return findPersonOutputPort.findAll()
    }
}