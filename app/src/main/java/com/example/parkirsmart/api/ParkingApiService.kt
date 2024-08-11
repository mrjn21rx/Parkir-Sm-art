package com.example.parkirsmart.api

import com.example.parkirsmart.model.ParkingResponse
import retrofit2.Call
import retrofit2.http.GET

interface ParkingApiService {
    @GET("lokasi_parkir_di_kota_bandung_2")
    fun getParkingLocations(): Call<ParkingResponse>
}
