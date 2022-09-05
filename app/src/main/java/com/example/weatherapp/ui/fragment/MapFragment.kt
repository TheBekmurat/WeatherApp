package com.example.weatherapp.ui.fragment

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.app.App
import com.example.weatherapp.databinding.FragmentMapBinding
import com.example.weatherapp.domain.BitmapDescriptor
import com.example.weatherapp.ui.base.BaseFragment
import com.example.weatherapp.ui.viewmodel.SharedViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : BaseFragment<FragmentMapBinding>(), OnMapReadyCallback {

    private val viewModel: SharedViewModel by activityViewModels<SharedViewModel> {
        (requireActivity().application as App).appComponent.viewModelsFactory()
    }

    private lateinit var coordinate: String

    private val bitmapDescrib: BitmapDescriptor by lazy {
        BitmapDescriptor.BitmapDescriptorFromVector(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment: SupportMapFragment =
            childFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment
        mapFragment.getMapAsync(this)
        navigateToToday()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.setOnMapClickListener {
            binding.btnSelect.visibility = View.VISIBLE
            coordinate = "${it.latitude},${it.longitude}"
            val markerOptions = MarkerOptions().position(it)
                .icon(bitmapDescrib.bitmapDescriptor(R.drawable.ic_vector))
            googleMap.clear()
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                it, 10F
            ))
            googleMap.addMarker(markerOptions)
        }
    }

    private fun navigateToToday() {
        binding.btnSelect.setOnClickListener {
            if (binding.btnSelect.visibility == View.VISIBLE) {
                viewModel.getWeather(coordinate)
                findNavController().navigate(R.id.action_mapFragment_to_todayFragment)
            }
        }
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMapBinding =
        FragmentMapBinding.inflate(inflater, container, false)
}