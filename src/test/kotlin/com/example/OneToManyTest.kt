package com.example

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@MicronautTest
class OneToManyTest(
    private val parentRepository: ParentRepository,
    private val childRepository: ChildRepository,
) {

    @BeforeEach
    fun setUp() {
        val childA = Child(name = "Child A")
        val childB = Child(name = "Child B")
        val parentOne = Parent(name = "Parent 1", children = listOf(childA, childB))
        parentRepository.save(parentOne)

        val parentTwo = Parent(name = "Parent 2")
        val childC = Child(name = "Child C", parent = parentTwo)
        childRepository.save(childC)
    }

    @AfterEach
    fun clean() {
        childRepository.deleteAll()
        parentRepository.deleteAll()
    }

    @Test //THIS TEST FAILS
    fun `Parent should have child when saved by parent`() {
        val actual = parentRepository.findAll().toList().filter { it.name == "Parent 1" }[0]

        assertEquals(2, actual.children.size)
    }

    @Test //THIS TEST FAILS
    fun `Child should have parent when saved by parent`() {
        val actual = childRepository.findAll().toList().filter { it.name != "Child C" }

        assertEquals(2, actual.size) // This assert is ok
        assertNotNull(actual[0].parent)
        assertNotNull(actual[1].parent)
    }

    @Test //THIS TEST IS FINE
    fun `Parent should have child when saved by child`() {
        val actual = parentRepository.findAll().toList().filter { it.name == "Parent 2" }[0]

        assertEquals(1, actual.children.size)
    }

    @Test //THIS TEST IS FINE
    fun `Child should have parent when saved by child`() {
        val actual = childRepository.findAll().toList().filter { it.name == "Child C" }[0]

        assertNotNull(actual.parent)
    }

    @Test
    fun read() {
        for (p in parentRepository.findAll()) println(p)
        for (c in childRepository.findAll()) println(c)
    }
}

