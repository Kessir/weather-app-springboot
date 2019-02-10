package com.kessir.weatherreport.utils

import com.kessir.weatherreport.domain.model.Location
import org.junit.Test

import org.junit.Assert.*

class CsvParserTest {

    @Test
    fun `csv file is successfully loaded and parsed`() {
        val locations = CsvParser().loadLocationsList("locations.csv")
        assertFalse(locations.isEmpty())
    }

    @Test
    fun `csv data is loaded to the correct fields`() {
        val firstLocation = CsvParser().loadLocationsList("locations.csv")[0]

        val expected = Location(
                city = "Helsinki",
                countryCode = "FI",
                latitude = 60.16,
                longitude = 24.93,
                minTempLimit = -5.0,
                maxTempLimit = 35.0,
                id = firstLocation.id)

        assertEquals(expected, firstLocation)
    }
}
