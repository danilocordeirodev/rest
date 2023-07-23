package com.oriedroc.rest.application.ports.out

import com.oriedroc.rest.application.core.domain.Person

interface DeletePersonOutputPort {
    fun delete(person: Person)
}