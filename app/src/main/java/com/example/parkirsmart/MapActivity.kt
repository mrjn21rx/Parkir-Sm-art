package com.example.parkirsmart

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.parkirsmart.api.ParkingApiService
import com.example.parkirsmart.model.ParkingResponse
import com.mapbox.geojson.Point
import com.mapbox.maps.Mapbox
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.AnnotationPlugin
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapActivity : AppCompatActivity() {

    private lateinit var parkingApiService: ParkingApiService
    private lateinit var mapView: MapView
    private lateinit var mapboxMap: MapboxMap
    private lateinit var pointAnnotationManager: PointAnnotationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, "sk.eyJ1IjoibWFyamFuMTEyMiIsImEiOiJjbHptYXFsMDgwODFqMmtxdTg4a2FpZHJsIn0.zRwn3vdqW36cRu5u4WMtVg")
        setContentView(R.layout.activity_map)

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync { mapboxMap ->
            this.mapboxMap = mapboxMap
            val annotationPlugin = mapboxMap.getPlugin(AnnotationPlugin::class.java)
            pointAnnotationManager = annotationPlugin.createPointAnnotationManager()
            mapboxMap.loadStyleUri(Style.MAPBOX_STREETS) {
                fetchParkingLocations()
            }
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://opendata.bandung.go.id/api/bigdata/dinas_perhubungan/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        parkingApiService = retrofit.create(ParkingApiService::class.java)
    }

    private fun fetchParkingLocations() {
        parkingApiService.getParkingLocations().enqueue(object : Callback<ParkingResponse> {
            override fun onResponse(call: Call<ParkingResponse>, response: Response<ParkingResponse>) {
                if (response.isSuccessful) {
                    val parkingResponse = response.body()
                    parkingResponse?.data?.forEach { location ->
                        val pointAnnotationOptions = PointAnnotationOptions()
                            .withPoint(Point.fromLngLat(location.longitude, location.latitude))
                            .withIconImage("your-actual-icon-id") // Replace with the actual icon ID

                        pointAnnotationManager.create(pointAnnotationOptions)
                    }
                } else {
                    Log.e("MapActivity", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ParkingResponse>, t: Throwable) {
                Log.e("MapActivity", "Failure: ${t.message}")
            }
        })
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
