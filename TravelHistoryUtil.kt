package com.example.trainapp.util

class TravelHistoryUtil {
    companion object {
        fun needsExtraEquipment(from: String, to: String): Boolean {
            // This is a simplified example. In a real app, you'd have more complex logic
            // or perhaps check against a database of routes that require extra equipment.
            return from.lowercase() == "mountain station" || to.lowercase() == "mountain station"
        }
    }
}

