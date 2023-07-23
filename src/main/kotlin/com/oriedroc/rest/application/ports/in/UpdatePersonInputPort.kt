package com.oriedroc.rest.application.ports.`in`

import com.oriedroc.rest.application.core.domain.Person
import java.util.UUID

interface UpdatePersonInputPort {

    fun update(person: Person, personUUID: UUID) : Person
}