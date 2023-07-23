package com.oriedroc.rest.application.ports.out

import com.oriedroc.rest.application.core.domain.Person
import java.util.UUID

interface UpdatePersonOutputPort {

    fun update(person: Person) : Person
}