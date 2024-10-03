package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class OfflineBusScheduleRepository(private val busScheduleDao: BusScheduleDao) : BusScheduleRepository {

    override fun getAllSchedules(): Flow<List<BusSchedule>> = busScheduleDao.getAllSchedulesStream()

    override fun getScheduleFor(stopName: String): Flow<List<BusSchedule>> = busScheduleDao.getScheduleForStream(stopName)

}