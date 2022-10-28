package com.pavel.avitoweatherapp.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pavel.avitoweatherapp.R
import com.pavel.avitoweatherapp.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var dayListAdapter: ForecastDayListAdapter
    private lateinit var weekListAdapter: ForecastWeekListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadForecast()
        observeViewModel()
        setupDayRecyclerView()
        setupWeekRecyclerView()

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_MainFragment_to_SettingsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupDayRecyclerView() {
        dayListAdapter = ForecastDayListAdapter()
        binding.recyclerViewDay.apply {
            adapter = dayListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupWeekRecyclerView() {
        weekListAdapter = ForecastWeekListAdapter()
        binding.recyclerViewWeek.apply {
            adapter = weekListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun observeViewModel() {
        viewModel.forecastResponse.observe(viewLifecycleOwner) {
            if (it != null) {
                dayListAdapter.submitList(it.drop(1).take(7))
                weekListAdapter.submitList(it.slice(5 until it.size step 8))
                binding.apply {
                    textViewTemp.text = "${it[0].main.temp.toString()/*TODO*/}℃"
                    textPressure.text = "${it[0].main.pressure.toString()/*TODO*/} мм рт. ст."
                    textWind.text = "${it[0].wind.speed.toString()/*TODO*/} м/с"
                    Glide.with(requireContext())
                        .load("http://openweathermap.org/img/wn/${it[0].weather[0].icon}@2x.png")
                        .fitCenter()
                        .into(imageViewWeather)
                }
            }
        }

        viewModel.cityName.observe(viewLifecycleOwner) {
            binding.textViewCity.text = it
        }
    }

}