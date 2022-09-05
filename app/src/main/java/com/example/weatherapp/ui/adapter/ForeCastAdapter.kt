package com.example.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.model.weather.Forecastday
import com.example.weatherapp.databinding.WeekItemViewBinding
import com.example.weatherapp.domain.DateFormatUseCase
import com.example.weatherapp.domain.MMM_DD
import com.example.weatherapp.domain.YYYY_MM_DD
import kotlin.math.roundToInt

class ForeCastAdapter(
    private val dateFormatUseCase: DateFormatUseCase
): RecyclerView.Adapter<ForeCastAdapter.ViewHolder>() {

    var foreCast: List<Forecastday> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: WeekItemViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(WeekItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foreCast = foreCast[position]
        holder.binding.apply {
            tvDay.text = dateFormatUseCase.execute(foreCast.date, MMM_DD, YYYY_MM_DD)
            tvTemp.text = "${foreCast.day.maxtemp_c.roundToInt()}°C"
        }
    }

    override fun getItemCount(): Int = foreCast.size
}