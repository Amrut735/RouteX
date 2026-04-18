package com.routex.app.student.domain.usecase

import com.routex.app.eta.domain.model.BusStatus
import com.routex.app.eta.domain.model.EtaResult
import javax.inject.Inject

/**
 * Predicts whether the student is likely to miss their bus, and suggests
 * the next available action.
 *
 * Inputs:
 *   - [etaResult]        : current ETA snapshot from [ObserveEtaUseCase]
 *   - [walkingTimeMin]   : estimated walking time to the boarding stop (default 5 min)
 *   - [bufferMin]        : extra time margin the student wants (default 2 min)
 *
 * Output: [MissedBusPrediction]
 */
class MissedBusPredictionUseCase @Inject constructor() {

    operator fun invoke(
        etaResult: EtaResult,
        walkingTimeMin: Float = 5f,
        bufferMin: Float      = 2f,
    ): MissedBusPrediction {
        val bus = etaResult

        // Bus is offline — can't predict
        if (bus.status == BusStatus.OFFLINE) {
            return MissedBusPrediction(
                likelihood = MissLikelihood.UNKNOWN,
                reason     = "Bus is currently offline. Check back later.",
                suggestion = "Wait for bus to come online.",
            )
        }

        // Bus already arrived / at stop
        if (bus.isAtStop) {
            return MissedBusPrediction(
                likelihood     = MissLikelihood.BUS_AT_STOP,
                reason         = "Bus is at your stop right now!",
                suggestion     = "Head out immediately.",
                urgentAction   = "RUN NOW",
            )
        }

        val timeNeeded = walkingTimeMin + bufferMin
        val eta        = bus.etaMinutes

        return when {
            // Already too late
            eta < walkingTimeMin -> MissedBusPrediction(
                likelihood   = MissLikelihood.WILL_MISS,
                reason       = "Bus arrives in ${eta.toInt()} min but you need ${walkingTimeMin.toInt()} min to walk.",
                suggestion   = "You may miss this bus. Check for the next departure.",
                urgentAction = "Check next bus",
            )

            // Tight — possible to catch if you leave NOW
            eta in walkingTimeMin..(timeNeeded + 1f) -> MissedBusPrediction(
                likelihood   = MissLikelihood.TIGHT,
                reason       = "ETA is ${eta.toInt()} min. You need to leave in the next 1–2 minutes.",
                suggestion   = "Leave immediately to catch the bus.",
                urgentAction = "LEAVE NOW",
            )

            // Comfortable window
            eta in (timeNeeded + 1f)..(timeNeeded + 8f) -> MissedBusPrediction(
                likelihood = MissLikelihood.ON_TRACK,
                reason     = "Bus arrives in ${eta.toInt()} min. You have time.",
                suggestion = "Start heading to ${bus.stopName} in the next ${(eta - timeNeeded).toInt()} min.",
            )

            // Bus delayed
            bus.status == BusStatus.DELAYED -> MissedBusPrediction(
                likelihood = MissLikelihood.DELAYED,
                reason     = "Bus is running late (ETA: ${bus.etaFormatted}).",
                suggestion = "No rush — delay detected. Leave in ${(eta - timeNeeded).toInt()} min.",
            )

            // Plenty of time
            else -> MissedBusPrediction(
                likelihood = MissLikelihood.PLENTY_OF_TIME,
                reason     = "Bus arrives in ${eta.toInt()} min. Plenty of time!",
                suggestion = "You can leave in ${(eta - timeNeeded).toInt()} minutes.",
            )
        }
    }
}

data class MissedBusPrediction(
    val likelihood: MissLikelihood,
    val reason: String,
    val suggestion: String,
    val urgentAction: String? = null,
)

enum class MissLikelihood(
    val label: String,
    val colorHex: Long,
    val emoji: String,
) {
    WILL_MISS(        "Will Miss",      0xFFE74C3C, "❌"),
    TIGHT(            "Tight!",         0xFFE67E22, "⚡"),
    BUS_AT_STOP(      "Bus Is Here!",   0xFF9B59B6, "🚌"),
    ON_TRACK(         "On Track",       0xFF2ECC71, "✅"),
    DELAYED(          "Bus Delayed",    0xFFF39C12, "⏳"),
    PLENTY_OF_TIME(   "Plenty of Time", 0xFF3498DB, "😊"),
    UNKNOWN(          "Unknown",        0xFF95A5A6, "❓"),
}
