package com.oriedroc.rest.application.ports.`in`

import java.util.UUID

interface DeletePersonInputPort {
    fun delete(personUuid: UUID)
}