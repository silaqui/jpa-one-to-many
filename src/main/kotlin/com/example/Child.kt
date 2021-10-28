package com.example

import java.util.*
import javax.persistence.*

@Entity
data class Child(
    @Id
    @GeneratedValue
    var id: UUID? = null,
    var name: String = "",
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var parent: Parent? = null,
) {

    override fun toString(): String = "{name: ${name}, parent: ${parent?.name}}"
}