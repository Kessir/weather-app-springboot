package com.kessir.weatherreport

import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.springframework.http.HttpStatus

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApiIntegrationTest {
    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    @Throws(Exception::class)
    fun `get list of locations`() {
        val result = restTemplate.getForEntity("/api/locations", String::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result.statusCode)
    }


    @Test
    @Throws(Exception::class)
    fun `get list of temperatures by locations`() {
        val result = restTemplate.getForEntity("/api/weather", String::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result.statusCode)
    }
}
