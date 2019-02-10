package com.kessir.weatherreport.services

import com.kessir.weatherreport.domain.AppDataRepository
import com.kessir.weatherreport.domain.model.AppData
import org.springframework.stereotype.Service

@Service
class AppDataService(val appDataRepository: AppDataRepository) {

    fun isAppDataSetUp(): Boolean  =  appDataRepository.count() > 0

    fun getFetchIntervalInSecs(): Int {
        val result = appDataRepository.findById(APP_DATA_ID)

        return if (result.isPresent) {
            result.get().dataFetchIntervalSec
        } else {
            DEFAULT_INTERVAL_IN_S
        }
    }

    fun updateData(dataFetchIntervalSec: Int = DEFAULT_INTERVAL_IN_S) {
        appDataRepository.save(
                AppData(id = APP_DATA_ID,
                dataFetchIntervalSec = dataFetchIntervalSec))
    }

    companion object {
        private const val DEFAULT_INTERVAL_IN_S = 600
        private const val APP_DATA_ID = "app-data"
    }
}
