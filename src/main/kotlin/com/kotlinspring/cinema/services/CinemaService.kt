package com.kotlinspring.cinema.services

import com.kotlinspring.cinema.domain.Seat
import org.springframework.stereotype.Service
import java.math.BigDecimal

/**
 * @author Bahaa Farouk
 * Demo for Kotlin Spring Implementation
 */

@Service
class CinemaService {

    /*
    The problem statement

    SEAT PRICES:
        Seats in rows 14 and 15 cost 14.50
        Seats in rows 1 to 13 and numbered 1 to 3 or 34 to 36 cost 16.50
        All other seats in row 1 cost 21.00
        All other seats cost 18.00
    SEAT DESCRIPTIONS:
        Seats in row 15: "Back row"
        Seats in row 14: "Cheaper seat"
        All other rows, seats 1 to 3 and 34 to 36: "Restricted View"
        All other seats in rows 1 and 2 "Best view"
        All other seats: "Standard seat"
    */

    private val cinemaSeats = mutableListOf<Seat>()

    constructor() {

        fun getPrice(row: Char, col: Int) : BigDecimal {
            return when {
                row >='N' -> BigDecimal(14.50)
                col <=3 || col >= 34 -> BigDecimal(16.50)
                row == 'A' -> BigDecimal(21)
                else -> BigDecimal(18)
            }
        }

        fun getDescription(row: Char, col: Int) : String {
            return when {
                row == 'O' -> "Back Row"
                row == 'N' -> "Cheaper Seat"
                col <=3 || col >= 34 -> "Restricted View"
                row <='B' -> "Best View"
                else -> "Standard Seat"
            }
        }

        // building the cinema seats upon defined logic
        for (row in 'A'..'O') {
            for (col in 1..36) {
                cinemaSeats.add(Seat(0, row, col, getPrice(row,col), getDescription(row,col)))
            }
        }
    }

    val seats
        get() = cinemaSeats.toList()

    fun findSeat(col :Int, row :Char) : Seat {
        return seats.filter { it.rowe == row && it.col == col }.first()
    }

}