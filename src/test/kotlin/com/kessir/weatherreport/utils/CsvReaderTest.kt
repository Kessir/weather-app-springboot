package com.kessir.weatherreport.utils

import com.kessir.weatherreport.data.model.Location
import org.junit.Test

import org.junit.Assert.*

class CsvReaderTest {

    @Test
    fun `csv file is loaded and parsed`() {
        val locations = CsvReader().loadLocationsList("locations.csv")
        assertFalse(locations.isEmpty())
    }

    @Test
    fun `csv data is loaded to the correct fields`() {
        val firstLocation = CsvReader().loadLocationsList("locations.csv")[0]

        val expected = Location(
                city = "Helsinki",
                countryCode = "FI",
                lat = 60.16,
                lon = 24.93,
                minTempLimit = -5.0,
                maxTempLimit = 35.0,
                id = firstLocation.id)

        assertEquals(expected, firstLocation)
    }
}
