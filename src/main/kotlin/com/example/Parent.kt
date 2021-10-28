package com.example

import java.util.*
import javax.persistence.*

@Entity
data class Parent(
    @Id
    @GeneratedValue
    var id: UUID? = null,
    var name: String,
    @OneToMany(mappedBy = "parent",cascade = [CascadeType.ALL])
    var children: List<Child> = listOf(),
    ) {
    override fun toString() = "{name: ${this.name}, children: ${children.map { it.name }}}"
}
