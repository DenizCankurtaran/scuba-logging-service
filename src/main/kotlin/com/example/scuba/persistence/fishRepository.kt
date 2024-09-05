package com.example.scuba.persistence
import org.springframework.data.repository.CrudRepository

interface FishRepository : CrudRepository<Fish, Long>