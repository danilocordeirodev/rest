package com.oriedroc.rest.adapters.out.persistence.repository

import com.oriedroc.rest.adapters.out.persistence.entity.PersonEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PersonRepository : JpaRepository<PersonEntity, Long>{
    fun findByUuid(uuid: UUID): PersonEntity?
}