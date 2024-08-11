package com.example.parkirsmart.model

data class ParkingLocation(
    val id: String,      // ID lokasi parkir
    val name: String,    // Nama lokasi parkir
    val latitude: Double, // Lintang (koordinat)
    val longitude: Double, // Bujur (koordinat)
    val capacity: Int     // Kapasitas parkir
)
