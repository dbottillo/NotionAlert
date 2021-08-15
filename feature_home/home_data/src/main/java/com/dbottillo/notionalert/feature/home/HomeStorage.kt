package com.dbottillo.notionalert.feature.home

import kotlinx.coroutines.flow.Flow
import java.time.OffsetDateTime

interface HomeStorage {
    suspend fun updateTimestamp()
    val timestamp: Flow<OffsetDateTime>
}
