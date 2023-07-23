package com.oriedroc.rest.application.core.usecase

import com.oriedroc.rest.application.core.exception.ResourceNotFoundException
import com.oriedroc.rest.application.ports.`in`.DeletePersonInputPort
import com.oriedroc.rest.application.ports.out.DeletePersonOutputPort
import com.oriedroc.rest.application.ports.out.FindPersonOutputPort
import org.springframework.stereotype.Service
import java.util.*
import java.util.logging.Logger

@Service
class DeletePersonUseCase(
    private val deletePersonOutputPort: DeletePersonOutputPort,
    private val findPersonOutputPort: FindPersonOutputPort
) : DeletePersonInputPort {

    private val logger = Logger.getLogger(DeletePersonUseCase::class.java.name)
    override fun delete(personUuid: UUID) {
        logger.info("Deleting one person with UUID $personUuid!")

        val person = findPersonOutputPort.findByUuid(personUuid)
            ?: throw ResourceNotFoundException("No records found for this UUID!");

        deletePersonOutputPort.delete(person)
    }

}