package com.kessir.weatherreport.utils

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvParser
import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource


class CsvReader {
    private val logger = LoggerFactory.getLogger(CsvReader::class.java)
    private val mapper = CsvMapper().enable(CsvParser.Feature.TRIM_SPACES)
    private val schema = mapper.schemaFor(Location::class.java).withHeader()

    fun loadLocationsList(fileName: String): List<com.kessir.weatherreport.data.model.Location> {
        return try {
            val file = ClassPathResource(fileName).file

            val readValues = mapper.readerFor(Location::class.java).with(schema).readValues<Location>(file)

            logger.debug("Loading initial list of locations from file")

            return readValues.readAll().map { it.toLocation() }
        } catch (e: Exception) {
            logger.error("Error occurred while loading location list from file $fileName", e)
            emptyList()
        }

    }
}

@JsonPropertyOrder(value = ["city", "countryCode", "lat", "lon", "minTempLimit", "maxTempLimit"])
private data class Location(
        val city: String? = null,
        val countryCode: String? = null,
        val lat: Double? = null,
        val lon: Double? = null,
        val minTempLimit: Double? = null,
        val maxTempLimit: Double? = null)

private fun Location.toLocation(): com.kessir.weatherreport.data.model.Location = com.kessir.weatherreport.data.model.Location(city!!, countryCode!!, lat!!, lon!!, minTempLimit!!, maxTempLimit!!)
