package com.oriedroc.rest.adapters.`in`.controller.response

import jakarta.validation.constraints.NotBlank
import java.util.UUID

data class PersonResponse (

    var id: Long,

    var uuid: UUID,

    var firstName: String,

    var lastName: String,

    var gender: String
)