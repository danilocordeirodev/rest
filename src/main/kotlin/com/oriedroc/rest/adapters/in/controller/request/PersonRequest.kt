package com.oriedroc.rest.adapters.`in`.controller.request

import jakarta.validation.constraints.NotBlank


data class PersonRequest (
    @NotBlank
    var firstName: String,
    @NotBlank
    var lastName: String,
    @NotBlank
    var gender: String
)
