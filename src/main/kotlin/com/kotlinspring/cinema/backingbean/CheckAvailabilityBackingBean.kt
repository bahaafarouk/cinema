package com.kotlinspring.cinema.backingbean

import com.kotlinspring.cinema.domain.Booking
import com.kotlinspring.cinema.domain.Movie
import com.kotlinspring.cinema.domain.Seat

/**
 * @author Bahaa Farouk
 * Demo for Kotlin Spring Implementation
 */

class CheckAvailabilityBackingBean {

    var selectedSeatCol : Int = 1
    var selectedSeatRow : Char = 'A'
    var movieId : Long? = null
    var customerName : String = ""
    var available : Boolean? = null
    var selectedSeat : Seat? = null
    var selectedMovie : Movie? = null
    var booking : Booking? = null

}