package com.kotlinspring.cinema.services

import com.kotlinspring.cinema.data.BookingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

/**
 * @author Bahaa Farouk
 * Demo for Kotlin Spring Implementation
 */

@Service
class ReportingService {

    @Autowired
    lateinit var bookingRepository: BookingRepository

    fun allBookings() : String {
        val bookings = bookingRepository.findAll()
        val htmlBookings = bookings.map {"<tr><td>${it.movie.title}</td><td>${it.seat}</td><td>${it.customerName}</td></tr>"}
        val reportHeader = "<table><tr><th>Movie</th><th>Seat</th><th>Customer</th></tr>"
        val reportFooter = "</table>"
        return "${reportHeader}${htmlBookings.joinToString()}${reportFooter}"
    }

    fun premiumBookings() : String {
        val bookings = bookingRepository.findAll()
        val htmlBookings = bookings
                .filter {it.seat.price >= BigDecimal(18) }
                .map {"<tr><td>${it.movie.title}</td><td>${it.seat}</td><td>${it.customerName}</td></tr>"}
        val reportHeader = "<table><tr><th>Movie</th><th>Seat</th><th>Customer</th></tr>"
        val reportFooter = "</table>"
        return "${reportHeader}${htmlBookings.joinToString()}${reportFooter}"
    }

}