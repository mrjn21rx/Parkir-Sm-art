package com.example.parkirsmart.model

data class ParkingResponse(
    val message: String,                // Pesan dari API
    val data: List<ParkingLocation>     // List dari lokasi parkir
)
