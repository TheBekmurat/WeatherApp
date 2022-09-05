package com.example.weatherapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.R
import com.example.weatherapp.app.App
import com.example.weatherapp.databinding.FragmentTodayBinding
import com.example.weatherapp.domain.DateFormatUseCase
import com.example.weatherapp.domain.YYYY_MMM_DD_HH_MM
import com.example.weatherapp.domain.dd_MMM_YYYY_HH_MM
import com.example.weatherapp.ui.adapter.TodayAdapter
import com.example.weatherapp.ui.base.BaseFragment
import com.example.weatherapp.ui.viewmodel.SharedViewModel
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import kotlin.math.roundToInt

class TodayFragment : BaseFragment<FragmentTodayBinding>() {

    private val viewModel: SharedViewModel by activityViewModels {
        (requireActivity().application as App).appComponent.viewModelsFactory()
    }

    private val sdklfsdn: String? = null

    private val dateFormatUseCase: DateFormatUseCase = DateFormatUseCase.DateFormatUseCase()

    private lateinit var adapter: TodayAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.mutableLiveData.observe(viewLifecycleOwner) {
            if (it.isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                adapter.hours = it.weatherModel?.forecast!!.forecastday[0].hour
                binding.currentTemp.text = "${it.weatherModel.current.temp_c.roundToInt()}°C"
                binding.tvCondition.text = it.weatherModel.current.condition.text
                binding.tvCity.text = it?.weatherModel.location.name
                binding.currentDate.text =
                    dateFormatUseCase.execute(it?.weatherModel.location.localtime,
                        dd_MMM_YYYY_HH_MM, YYYY_MMM_DD_HH_MM)

            }
        }
    }

//    private fun initAutoComplete() {
//        val api_key = "AIzaSyBot6xtK4vJm_i4t8ukjkQWH7izO79vGp4"
//
//        if (!Places.isInitialized()) {
//            Places.initialize(requireContext().applicationContext, api_key)
//        }
//
//        Places.createClient(requireContext())
//
//        val autoCompeteSupportFragment =
//            childFragmentManager.findFragmentById(R.id.autocompete_fragment) as AutocompleteSupportFragment
//
//        autoCompeteSupportFragment.setPlaceFields(listOf(Place.Field.ID,
//            Place.Field.LAT_LNG,
//            Place.Field.NAME))
//
//        autoCompeteSupportFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
//            override fun onError(status: Status) {
//                Log.d("TAG", status.statusMessage.toString())
//            }
//
//            override fun onPlaceSelected(place: Place) {
//                val latLang = place.latLng
//                viewModel.getWeather("${latLang.latitude},${latLang.longitude}")
//            }
//        })
//    }

    private fun initAdapter() {
        adapter = TodayAdapter(dateFormatUseCase)
        binding.recyclerView.adapter = adapter
    }

    override fun initBinding(
        inflater: LayoutInflater, container: ViewGroup?,
    ): FragmentTodayBinding = FragmentTodayBinding.inflate(inflater, container, false)

}