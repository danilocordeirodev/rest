package com.oriedroc.rest.adapters.out.persistence.entity

import com.oriedroc.rest.common.adapter.jpa.BaseEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "persons")
class PersonEntity (
    override var id: Long? = null,

    @Column(unique = true, updatable = false, nullable = false)
    var uuid: UUID? = null,

    @Column(name = "first_name", nullable = false, length = 80)
    var firstName: String = "",

    @Column(name = "last_name", nullable = false, length = 80)
    var lastName: String = "",

    @Column(nullable = false)
    var gender: String = "",

) : BaseEntity() {
    @PrePersist
    fun initializeUUID() {
        if (uuid == null) {
            uuid = UUID.randomUUID()
        }
    }
}