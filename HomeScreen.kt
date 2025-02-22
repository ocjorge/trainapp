package com.example.trainapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trainapp.databinding.FragmentHomeScreenBinding

class HomeScreen : Fragment() {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonTickets.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_ticketsFragment)
        }

        binding.buttonMap.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_mapFragment)
        }

        binding.buttonAdvice.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_adviceFragment)
        }

        binding.buttonAlerts.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_alertsFragment)
        }

        binding.buttonIncidents.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_incidentFragment)
        }

        binding.buttonProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_profileFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

