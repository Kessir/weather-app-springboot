package com.kessir.weatherreport.web

import com.kessir.weatherreport.domain.model.Location
import com.kessir.weatherreport.domain.model.LocationTemps
import com.kessir.weatherreport.services.LocationsService
import com.kessir.weatherreport.services.TemperaturesService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("api")
class LocationsController(val locationsService: LocationsService) {

    @GetMapping("/locations")
    fun getLocations(): List<Location> {
        return locationsService.getAll()
    }

    @GetMapping("/locations/{id}")
    fun getLocation(@PathVariable id: String): Optional<Location> {
        return locationsService.findById(id)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private fun handleVinNotFound(ex: LocationNotFoundException) {
    }
}

class LocationNotFoundException : Exception() {

}
