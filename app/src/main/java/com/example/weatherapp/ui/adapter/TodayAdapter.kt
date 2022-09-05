package com.example.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.model.weather.Hour
import com.example.weatherapp.databinding.TodayItemViewBinding
import com.example.weatherapp.domain.DateFormatUseCase
import com.example.weatherapp.domain.HH_MM
import com.example.weatherapp.domain.YYYY_MMM_DD_HH_MM
import kotlin.math.roundToInt

class TodayAdapter(
    private val dateFormatUseCase: DateFormatUseCase,

    ) : RecyclerView.Adapter<TodayAdapter.ViewHolder>() {

    var hours: List<Hour> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(val binding: TodayItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(TodayItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         hours[position].apply {
            holder.binding.tvHours.text =
                dateFormatUseCase.execute(time, HH_MM, YYYY_MMM_DD_HH_MM)
            holder.binding.tvTemp.text = "${temp_c.roundToInt()}°C"
            holder.binding.tvCondition.text = condition.text
        }
    }

    override fun getItemCount(): Int = hours.size
}