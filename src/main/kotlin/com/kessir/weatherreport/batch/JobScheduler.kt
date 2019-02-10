package com.kessir.weatherreport.batch

import com.kessir.weatherreport.domain.LocationsRepository
import com.kessir.weatherreport.services.WeatherClassifier
import com.kessir.weatherreport.domain.WeatherDataSource
import com.kessir.weatherreport.domain.WeatherRepository
import com.kessir.weatherreport.domain.model.Location
import org.springframework.batch.core.Job
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.JobParameters
import java.util.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.batch.core.launch.JobLauncher


@Component
class ScheduledTasks(val jobLauncher: JobLauncher) {

    @Autowired
    lateinit var job: Job

    @Scheduled(fixedDelayString = "\${fetch.rate:${Companion.DEFAULT_INTERVAL_IN_MS}}")
    fun launchJob() {
        println("Job Started at :" + Date())

        val param = JobParametersBuilder().addString("JobID", System.currentTimeMillis().toString())
                .toJobParameters()

        val execution = jobLauncher.run(job, param)

        println("Job finished with status :" + execution.getStatus())
    }


    companion object {
        const val DEFAULT_INTERVAL_IN_MS = 600_000L
    }
}
