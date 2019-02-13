package com.kessir.weatherreport.services

import com.kessir.weatherreport.domain.model.AlertStatus
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
class WeatherClassificationLogicTest {

    private val weatherClassificationLogic = WeatherClassificationLogic()

    private val limits = Pair(-5.0, 30.0)

    @Test
    fun `should return NORMAL when temperatures are within limits`() {
        val temp = Pair(20.0, 21.0)

        val result = weatherClassificationLogic.getStatus(
                lowerLimit = limits.first,
                upperLimit = limits.second,
                minTemp = temp.first,
                maxTemp = temp.second)

        assertEquals(result, AlertStatus.NORMAL)
    }

    @Test
    fun `should return NORMAL when temperatures match upper and lower limit`() {
        val temp = Pair(limits.second, limits.second)

        val result = weatherClassificationLogic.getStatus(
                lowerLimit = limits.first,
                upperLimit = limits.second,
                minTemp = temp.first,
                maxTemp = temp.second)

        assertEquals(result, AlertStatus.NORMAL)
    }

    @Test
    fun `should return HIGH when max temperature exceeds upper limit`() {
        val temp = Pair(35.0, 32.0)

        val result = weatherClassificationLogic.getStatus(
                lowerLimit = limits.first,
                upperLimit = limits.second,
                minTemp = temp.first,
                maxTemp = temp.second)

        assertEquals(result, AlertStatus.EXTREME_HIGH)
    }

    @Test
    fun `should return LOW when min temperature is below lower limit`() {
        val temp = Pair(-20.0, -15.0)

        val result = weatherClassificationLogic.getStatus(
                lowerLimit = limits.first,
                upperLimit = limits.second,
                minTemp = temp.first,
                maxTemp = temp.second)

        assertEquals(result, AlertStatus.EXTREME_LOW)
    }

    @Test
    fun `should return LOW_AND_HIGH when minTemp is lower than lowerLimit and maxTemp is above upperLimit`() {
        val temp = Pair(-6.0, 31.0)

        val result = weatherClassificationLogic.getStatus(
                lowerLimit = limits.first,
                upperLimit = limits.second,
                minTemp = temp.first,
                maxTemp = temp.second)

        assertEquals(result, AlertStatus.EXTREME_LOW_AND_HIGH)
    }


}
