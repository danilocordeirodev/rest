package com.oriedroc.rest.application.ports.`in`

import com.oriedroc.rest.application.core.domain.Person

interface CreatePersonInputPort {

    fun create(person: Person) : Person
}