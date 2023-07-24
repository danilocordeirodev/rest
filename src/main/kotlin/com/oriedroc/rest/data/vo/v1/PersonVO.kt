package com.oriedroc.rest.data.vo.v1

import jakarta.persistence.*

data class PersonVO (

    var id: Long = 0,

    var firstName: String = "",

    var lastName: String = "",

    var gender: String = "",

)