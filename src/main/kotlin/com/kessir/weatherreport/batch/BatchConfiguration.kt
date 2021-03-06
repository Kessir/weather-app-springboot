package com.kessir.weatherreport.batch

import com.kessir.weatherreport.domain.WeatherRepository
import com.kessir.weatherreport.domain.model.Location
import com.kessir.weatherreport.domain.model.LocationTemps
import com.kessir.weatherreport.services.WeatherClassificationService
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.data.MongoItemReader
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate


@Configuration
@EnableBatchProcessing
class BatchConfiguration(val jobBuilderFactory: JobBuilderFactory, val stepBuilderFactory: StepBuilderFactory, val mongoTemplate: MongoTemplate) {
    @Bean
    fun reader(): ItemReader<Location> {
        val mongoItemReader = MongoItemReader<Location>()
        mongoItemReader.setTemplate(mongoTemplate)
        mongoItemReader.setCollection("locations")
        mongoItemReader.setQuery("{isMonitored: true}")
        mongoItemReader.setTargetType(Location::class.java)
        val sort = HashMap<String, Sort.Direction>()
        sort["_id"] = Sort.Direction.ASC
        mongoItemReader.setSort(sort)

        return mongoItemReader
    }

    @Bean
    fun processor(weatherClassificationService: WeatherClassificationService): ItemProcessor<Location, LocationTemps> = object : ItemProcessor<Location, LocationTemps> {
        override fun process(item: Location): LocationTemps? {
            return weatherClassificationService.process(item)
        }

    }

    @Bean
    fun writer(weatherRepository: WeatherRepository) = LocationItemWriter(weatherRepository)


    @Bean
    fun importUserJob(listener: JobCompletionNotificationListener, step1: Step): Job {
        return jobBuilderFactory.get("classify-location-temperatures")
                .incrementer(RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build()
    }

    @Bean
    fun step1(writer: LocationItemWriter, weatherClassificationService: WeatherClassificationService): Step {
        return stepBuilderFactory.get("step1")
                .chunk<Location, LocationTemps>(1)
                .reader(reader())
                .processor(processor(weatherClassificationService))
                .writer(writer)
                .build()
    }
}
