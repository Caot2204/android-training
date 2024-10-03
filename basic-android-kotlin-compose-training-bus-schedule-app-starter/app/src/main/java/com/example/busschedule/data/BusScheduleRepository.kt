package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

interface BusScheduleRepository {

    fun getAllSchedules(): Flow<List<BusSchedule>>

    fun getScheduleFor(stopName: String): Flow<List<BusSchedule>>

}