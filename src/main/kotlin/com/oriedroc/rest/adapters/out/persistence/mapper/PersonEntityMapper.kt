package com.oriedroc.rest.adapters.out.persistence.mapper


import com.oriedroc.rest.adapters.out.persistence.entity.PersonEntity
import com.oriedroc.rest.application.core.domain.Person
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel

@Mapper(
    componentModel = ComponentModel.SPRING
)
interface PersonEntityMapper {

    fun toDomain(entity: PersonEntity): Person

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    fun toEntity(domain: Person): PersonEntity
}