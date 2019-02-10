package com.kessir.weatherreport.batch

import org.springframework.batch.core.listener.JobExecutionListenerSupport
import org.slf4j.LoggerFactory
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.JobExecution
import org.springframework.stereotype.Component

@Component
class JobCompletionNotificationListener: JobExecutionListenerSupport() {
    private val logger = LoggerFactory.getLogger(JobCompletionNotificationListener::class.java)

    override fun  afterJob(jobExecution: JobExecution) {
        if (jobExecution.status === BatchStatus.COMPLETED) {
            logger.info("!!! JOB FINISHED! Time to verify the results")
        }
    }
}
