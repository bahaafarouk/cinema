package com.kotlinspring.cinema.data

import com.kotlinspring.cinema.domain.Booking
import com.kotlinspring.cinema.domain.Seat
import com.kotlinspring.cinema.domain.Movie
import org.springframework.data.jpa.repository.JpaRepository

interface SeatRepository : JpaRepository<Seat, Long>
interface MovieRepository : JpaRepository<Movie, Long>
interface BookingRepository : JpaRepository <Booking, Long>