package com.oriedroc.rest.application.ports.out

import com.oriedroc.rest.application.core.domain.Person

interface CreatePersonOutputPort {

    fun create(person: Person) : Person
}