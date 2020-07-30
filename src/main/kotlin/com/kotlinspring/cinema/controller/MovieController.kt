package com.kotlinspring.cinema.controller

import com.kotlinspring.cinema.data.MovieRepository
import com.kotlinspring.cinema.domain.Movie
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

/**
 * @author Bahaa Farouk
 * Demo for Kotlin Spring Implementation
 */

@Controller
@RequestMapping("/movies")
class MovieController() {

    @Autowired
    lateinit var movieRepository: MovieRepository

    @RequestMapping("")
    fun performancesHomePage() =
            ModelAndView("movies/home","movies", movieRepository.findAll())

    @RequestMapping("/add")
    fun addMovie() =
            ModelAndView("movies/add","movie", Movie(0, ""))

    @RequestMapping("save", method = arrayOf(RequestMethod.POST))
    fun saveMovie(movie: Movie) : String {
        movieRepository.save(movie)
        return "redirect:/movies/"
    }

}