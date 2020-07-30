package com.kotlinspring.cinema.domain

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * @author Bahaa Farouk
 * Demo for Kotlin Spring Implementation
 */

@Entity
data class Seat(@Id @GeneratedValue (strategy = GenerationType.AUTO)
                val id: Long,
                val rowe: Char,
                val col: Int,
                val price: BigDecimal,
                val description: String) {

    override fun toString(): String = "Seat $rowe-$col $$price ($description)"

}