package com.oriedroc.rest.adapters.`in`.controller.mapper

import com.oriedroc.rest.adapters.`in`.controller.request.PersonRequest
import com.oriedroc.rest.adapters.`in`.controller.response.PersonResponse
import com.oriedroc.rest.application.core.domain.Person
import org.mapstruct.Mapper
import org.mapstruct.Mapping


@Mapper(componentModel = "spring")
interface PersonMapper {
    @Mapping(target = "id", ignore = true)
    fun toDomain(personRequest: PersonRequest): Person

    fun toResponse(person: Person): PersonResponse
}