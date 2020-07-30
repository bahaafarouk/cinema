package com.kotlinspring.cinema.domain

import javax.persistence.*

/**
 * @author Bahaa Farouk
 * Demo for Kotlin Spring Implementation
 */

@Entity
data class Booking(@Id @GeneratedValue(strategy = GenerationType.AUTO)
              val id: Long,
              val customerName : String) {

    // initiated by hibernate (so it should be prefixed by lateinit)
    @ManyToOne
    lateinit var seat: Seat

    @ManyToOne
    lateinit var movie : Movie

}