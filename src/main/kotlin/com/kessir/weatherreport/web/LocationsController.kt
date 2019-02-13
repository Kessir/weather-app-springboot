package com.kessir.weatherreport.web

import com.kessir.weatherreport.domain.model.Location
import com.kessir.weatherreport.services.LocationsService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api")
class LocationsController(val locationsService: LocationsService) {

    @GetMapping("/locations")
    fun getLocations(): List<Location> {
        return locationsService.getAll()
    }

    @GetMapping("/locations/{id}")
    fun getLocation(@PathVariable id: String): Location {
        return locationsService.findById(id) ?: throw LocationNotFoundException()
    }
}

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Location not found")
class LocationNotFoundException : RuntimeException()
