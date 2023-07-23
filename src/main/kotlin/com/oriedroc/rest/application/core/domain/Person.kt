package com.oriedroc.rest.application.core.domain

import java.util.UUID

data class Person(
    val id: Long? = null,
    val uuid: UUID? = null,
    var firstName: String,
    var lastName: String,
    var gender: String
)
