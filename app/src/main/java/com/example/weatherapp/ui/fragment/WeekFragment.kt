package com.example.weatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.weatherapp.ui.viewmodel.SharedViewModel
import com.example.weatherapp.app.App
import com.example.weatherapp.data.model.uistate.UiState
import com.example.weatherapp.databinding.FragmentWeekBinding
import com.example.weatherapp.domain.DateFormatUseCase
import com.example.weatherapp.ui.adapter.ForeCastAdapter
import com.example.weatherapp.ui.base.BaseFragment


class WeekFragment : BaseFragment<FragmentWeekBinding>() {

    private val viewModel: SharedViewModel by activityViewModels {
        (requireActivity().application as App).appComponent.viewModelsFactory()
    }

    private lateinit var foreCastAdapter: ForeCastAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.mutableLiveData.observe(viewLifecycleOwner, object : Observer<UiState> {
            override fun onChanged(state: UiState) {
                state.weatherModel?.forecast?.forecastday.let {
                    if (it != null) {
                        foreCastAdapter.foreCast = it
                    }
                }
            }
        })
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentWeekBinding =
        FragmentWeekBinding.inflate(inflater, container, false)

    private fun initAdapter() {
        foreCastAdapter = ForeCastAdapter(DateFormatUseCase.DateFormatUseCase())
        binding.recyclerView.adapter = foreCastAdapter
    }

}