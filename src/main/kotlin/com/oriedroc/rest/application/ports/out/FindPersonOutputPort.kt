package com.oriedroc.rest.application.ports.out

import com.oriedroc.rest.application.core.domain.Person
import java.util.UUID

interface FindPersonOutputPort {
    fun findByUuid(uuid: UUID) : Person?

    fun findAll() : List<Person>
}