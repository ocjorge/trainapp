package com.example.trainapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.trainapp.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add markers for train stations
        val stations = listOf(
            Station("Central Station", LatLng(40.7128, -74.0060)),
            Station("North Station", LatLng(40.7500, -73.9967)),
            Station("South Station", LatLng(40.7061, -74.0092))
        )

        for (station in stations) {
            mMap.addMarker(MarkerOptions().position(station.location).title(station.name))
        }

        // Move camera to the first station
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stations.first().location, 12f))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    data class Station(val name: String, val location: LatLng)
}

