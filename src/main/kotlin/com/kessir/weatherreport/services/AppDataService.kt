package com.kessir.weatherreport.services

import com.kessir.weatherreport.data.AppDataRepository
import com.kessir.weatherreport.data.model.AppData
import com.kessir.weatherreport.data.model.Location
import org.springframework.stereotype.Service

@Service
class AppDataService(val appDataRepository: AppDataRepository) {

    fun getLocations(): List<Location> {
        val result = appDataRepository.findById(APP_DATA_ID)

        return if (result.isPresent) {
            result.get().locations
        } else {
            emptyList()
        }
    }

    fun isAppDataSetUp(): Boolean  =  appDataRepository.count() > 0

    fun getFetchIntervalInSecs(): Int {
        val result = appDataRepository.findById(APP_DATA_ID)

        return if (result.isPresent) {
            result.get().dataFetchIntervalSec
        } else {
            DEFAULT_INTERVAL_IN_S
        }
    }

    fun updateData(locations: List<Location>, dataFetchIntervalSec: Int = DEFAULT_INTERVAL_IN_S) {

        appDataRepository.save(
                AppData(id = APP_DATA_ID,
                dataFetchIntervalSec = dataFetchIntervalSec,
                locations = locations))
    }

    companion object {
        private const val DEFAULT_INTERVAL_IN_S = 600
        private const val APP_DATA_ID = "app-data"
    }
}
