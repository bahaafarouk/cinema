package com.kotlinspring.cinema.domain

import javax.persistence.*

/**
 * @author Bahaa Farouk
 * Demo for Kotlin Spring Implementation
 */

@Entity
data class Movie(@Id @GeneratedValue (strategy = GenerationType.AUTO)
            val id: Long,
            val title: String
            )
{
    // initiated by hibernate (so it should be prefixed by lateinit)
    @OneToMany (mappedBy = "movie")
    lateinit var bookings: List<Booking>
}
