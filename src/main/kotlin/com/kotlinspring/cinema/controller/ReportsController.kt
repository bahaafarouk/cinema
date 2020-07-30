package com.kotlinspring.cinema.controller

import com.kotlinspring.cinema.services.ReportingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.websocket.server.PathParam
import kotlin.reflect.full.declaredMemberExtensionProperties
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.memberFunctions

/**
 * @author Bahaa Farouk
 * Demo for Kotlin Spring Implementation
 */

@Controller
@RequestMapping("reports")
class ReportsController {

    @Autowired
    lateinit var reportingService : ReportingService

    // by reflection using ::
    fun getListOfReports() = reportingService::class.declaredMemberFunctions.map { it.name }

    @RequestMapping("")
    fun reports() = ModelAndView("reports", mapOf ("reports" to getListOfReports()))

    @RequestMapping("/getReport")
    fun getReport(@PathParam("reports") report : String) : ModelAndView{
        val matchedReport = reportingService::class.declaredMemberFunctions.filter { it.name == report }.firstOrNull()
        val reportBody = matchedReport?.call(reportingService) ?: ""
        return ModelAndView("reports", mapOf("reports" to getListOfReports(), "reportBody" to reportBody))
    }

}