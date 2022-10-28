package com.pavel.avitoweatherapp.presentation.settings

import android.Manifest
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pavel.avitoweatherapp.R
import com.pavel.avitoweatherapp.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private lateinit var cities: Array<String>
    private val viewModel by viewModels<SettingsViewModel>()
    private val binding get() = _binding!!
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        cities = resources.getStringArray(R.array.city_names)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.getCoordinatesByLocation()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCoordinates()
        observeViewModel()

        val adapter = ArrayAdapter<String>(
            requireContext(), android.R.layout.simple_dropdown_item_1line, cities
        )

        binding.autoCompleteTextView.setAdapter(adapter)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_SettingsFragment_to_MainFragment)
        }

        binding.buttonSave.setOnClickListener {
            viewModel.putCityName(binding.autoCompleteTextView.text.toString())
            viewModel.getCoordinatesByCityName()
        }

        binding.buttonUseCoord.setOnClickListener {
            viewModel.saveCoordByLocIntoSha()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.coordinatesResponse.observe(viewLifecycleOwner) {
            if (it != null) {
                viewModel.saveCoordinatesIntoSha()
            }
        }

        viewModel.cityName.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.autoCompleteTextView.text = Editable.Factory.getInstance().newEditable(it)
            }
        }

        viewModel.coordinates.observe(viewLifecycleOwner) {
            if (it != null && it.city != "") {
                binding.autoCompleteTextView.text =
                    Editable.Factory.getInstance().newEditable(it.city)
            }
        }
    }

}