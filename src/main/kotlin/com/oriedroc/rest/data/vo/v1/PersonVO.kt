package com.oriedroc.rest.data.vo.v1

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping
import jakarta.persistence.*
import org.springframework.hateoas.RepresentationModel

@JsonPropertyOrder("id", "firstName", "lastName", "gender")
data class PersonVO (

    @Mapping("id")
    @field:JsonProperty("id")
    var key: Long = 0,

    var firstName: String = "",

    var lastName: String = "",

    var gender: String = "",

) : RepresentationModel<PersonVO>()