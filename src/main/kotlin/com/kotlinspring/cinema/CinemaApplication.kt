package com.kotlinspring.cinema

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author Bahaa Farouk
 * Demo for Kotlin Spring Implementation
 */

@SpringBootApplication
class CinemaApplication

fun main(args: Array<String>) {
	runApplication<CinemaApplication>(*args)
}
