package com.kotlinspring.cinema.services

import com.kotlinspring.cinema.data.BookingRepository
import com.kotlinspring.cinema.data.SeatRepository
import com.kotlinspring.cinema.domain.Booking
import com.kotlinspring.cinema.domain.Movie
import com.kotlinspring.cinema.domain.Seat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

/**
 * @author Bahaa Farouk
 * Demo for Kotlin Spring Implementation
 */

@Service
class BookingService {

    @Autowired
    lateinit var bookingRepository: BookingRepository

    @Autowired
    lateinit var seatRepository : SeatRepository

    fun isSeatAvailable(seat : Seat, movie : Movie) : Boolean  = findBooking(seat,movie) == null

    fun findSeat(seatCol : Int, seatRow : Char) : Seat? {
        val allSeats = seatRepository.findAll()
        val foundSeat = allSeats.filter {it.col == seatCol && it.rowe == seatRow}
        return foundSeat.firstOrNull()
    }

    fun reserveSeat(selectedSeat: Seat, selectedMovie: Movie, customerName: String) : Booking{
        val booking = Booking(0,customerName)
        booking.movie = selectedMovie
        booking.seat = selectedSeat
        bookingRepository.save(booking)
        return booking
    }

    fun findBooking(selectedSeat: Seat, selectedMovie: Movie): Booking? {
        val bookings = bookingRepository.findAll()
        val matchedBooking = bookings.filter { it.seat == selectedSeat && it.movie == selectedMovie }
        return matchedBooking.firstOrNull()
    }

}