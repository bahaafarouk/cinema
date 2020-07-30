package com.kotlinspring.cinema.controller

import com.kotlinspring.cinema.backingbean.CheckAvailabilityBackingBean
import com.kotlinspring.cinema.data.MovieRepository
import com.kotlinspring.cinema.data.SeatRepository
import com.kotlinspring.cinema.domain.Movie
import com.kotlinspring.cinema.services.BookingService
import com.kotlinspring.cinema.services.CinemaService
import org.hibernate.annotations.Check
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

/**
 * @author Bahaa Farouk
 * Demo for Kotlin Spring Implementation
 */

@Controller
class MainController{

    // This is how legacy code in Java, dependency injection
    @Autowired (required = true)
    lateinit var cinemaService : CinemaService
    @Autowired (required = true)
    lateinit var bookingService: BookingService
    @Autowired
    lateinit var seatRepository: SeatRepository
    @Autowired
    lateinit var movieRepository: MovieRepository

    @RequestMapping("")
    fun homePage() : ModelAndView{
        val initialModel = mapOf ("bean"  to CheckAvailabilityBackingBean(),
                                "movies" to movieRepository.findAll(),
                                "seatCols" to 1..36,
                                "seatRows" to 'A'..'O')
        return ModelAndView("seatBooking", initialModel)
    }

    @RequestMapping("checkAvailability", method = arrayOf(RequestMethod.POST))
    fun checkAvailability(bean : CheckAvailabilityBackingBean) : ModelAndView{
        var selectedSeat = bookingService.findSeat(bean.selectedSeatCol,bean.selectedSeatRow)!!
        val selectedMovie = movieRepository.findById(bean.movieId!!).get()
        bean.selectedSeat = selectedSeat
        bean.selectedMovie = selectedMovie
        var bookingAvailable = bookingService.isSeatAvailable(selectedSeat, selectedMovie)
        bean.available = bookingAvailable

        if (!bookingAvailable){
            bean.booking = bookingService.findBooking(selectedSeat,selectedMovie)
        }

        val initialModel = mapOf ("bean"  to bean,
                "movies" to movieRepository.findAll(),
                "seatCols" to 1..36,
                "seatRows" to 'A'..'O')

        return ModelAndView("seatBooking", initialModel)
    }

    @RequestMapping("booking", method = arrayOf(RequestMethod.POST))
    fun book(bean: CheckAvailabilityBackingBean) : ModelAndView{
        val booking = bookingService.reserveSeat(bean.selectedSeat!!, bean.selectedMovie!!, bean.customerName)
        return ModelAndView("bookingConfirmed","booking",booking)
    }


    // to be done once, for initializing DB of seats
    @RequestMapping("bootstrap")
    fun createInitialData():ModelAndView{
        // persisting data from cinemaService
        seatRepository.saveAll(cinemaService.seats)
        return homePage()
    }
}