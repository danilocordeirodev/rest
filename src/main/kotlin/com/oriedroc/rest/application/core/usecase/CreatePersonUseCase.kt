package com.oriedroc.rest.application.core.usecase

import com.oriedroc.rest.application.core.domain.Person
import com.oriedroc.rest.application.ports.`in`.CreatePersonInputPort
import com.oriedroc.rest.application.ports.out.CreatePersonOutputPort
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class CreatePersonUseCase(
    private val createPersonOutputPort: CreatePersonOutputPort
) : CreatePersonInputPort{

    private val logger = Logger.getLogger(CreatePersonUseCase::class.java.name)
    override fun create(person: Person): Person {
        logger.info("Creating one person with name ${person.firstName}!")
        return createPersonOutputPort.create(person)
    }
}