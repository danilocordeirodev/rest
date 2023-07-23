package com.oriedroc.rest.adapters.`in`.controller

import com.oriedroc.rest.adapters.`in`.controller.mapper.PersonMapper
import com.oriedroc.rest.adapters.`in`.controller.request.PersonRequest
import com.oriedroc.rest.adapters.`in`.controller.response.PersonResponse
import com.oriedroc.rest.adapters.out.persistence.entity.PersonEntity
import com.oriedroc.rest.application.ports.`in`.CreatePersonInputPort
import com.oriedroc.rest.application.ports.`in`.DeletePersonInputPort
import com.oriedroc.rest.application.ports.`in`.FindPersonInputPort
import com.oriedroc.rest.application.ports.`in`.UpdatePersonInputPort
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@RestController
@RequestMapping("/person")
class PersonController(
    private val createPersonInputPort: CreatePersonInputPort,
    private val findPersonInputPort: FindPersonInputPort,
    private val updatePersonInputPort: UpdatePersonInputPort,
    private val deletePersonInputPort: DeletePersonInputPort,
    private val personMapper: PersonMapper
) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll(): ResponseEntity<MutableList<PersonResponse>> {
        val personResponseList = findPersonInputPort.findAll()
                                    .let { persons -> persons.map { personMapper.toResponse(it) } }

        return ResponseEntity(personResponseList.toMutableList(), HttpStatus.OK)
    }

    @GetMapping(value = ["/{uuid}"],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(@PathVariable(value="uuid") uuid: UUID): ResponseEntity<PersonResponse> {
        val personResponse = findPersonInputPort.findByUuid(uuid).let { personMapper.toResponse(it)}
        return ResponseEntity(personResponse, HttpStatus.OK)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@Valid @RequestBody personRequest: PersonRequest): ResponseEntity<PersonResponse> {
        val personResponse = createPersonInputPort.create(
            personMapper.toDomain(personRequest)
        ).let { personMapper.toResponse(it)}

        return ResponseEntity(personResponse, HttpStatus.CREATED)
    }

    @PutMapping(value = ["/{uuid}"],
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@PathVariable(value="uuid") uuid: UUID,
               @Valid @RequestBody personRequest: PersonRequest): ResponseEntity<PersonResponse> {

        val personResponse = updatePersonInputPort.update(
            personMapper.toDomain(personRequest), uuid
        ).let { personMapper.toResponse(it)}

        return ResponseEntity(personResponse, HttpStatus.OK)
    }

    @DeleteMapping(value = ["/{uuid}"],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(@PathVariable(value="uuid") uuid: UUID) : ResponseEntity<Void>{
        deletePersonInputPort.delete(uuid)
        return ResponseEntity.noContent().build()
    }
}