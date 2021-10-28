package com.example

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface ChildRepository : CrudRepository<Child, UUID>