package com.oriedroc.rest.common.adapter.jpa

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {
    @get:[Id GeneratedValue(strategy = GenerationType.IDENTITY) Column(updatable = false, nullable = false)]
    abstract var id: Long?

    @get:[CreationTimestamp Column(updatable = false, nullable = false)]
    var createdAt: LocalDateTime = LocalDateTime.now()

    @get:[UpdateTimestamp Column(nullable = false)]
    var updatedAt: LocalDateTime = LocalDateTime.now()
}