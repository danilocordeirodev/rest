package com.oriedroc.rest.application.ports.`in`

import com.oriedroc.rest.application.core.domain.Person
import java.util.UUID

interface FindPersonInputPort {
    fun findByUuid(uuid: UUID) : Person

    fun findAll() : List<Person>
}