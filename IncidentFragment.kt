package com.example.trainapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.trainapp.data.AppDatabase
import com.example.trainapp.data.Incident
import com.example.trainapp.databinding.FragmentIncidentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class IncidentFragment : Fragment() {

    private var _binding: FragmentIncidentBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIncidentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = AppDatabase.getInstance(requireContext())

        binding.reportButton.setOnClickListener {
            val description = binding.descriptionEditText.text.toString()
            val location = binding.locationEditText.text.toString()

            if (description.isNotEmpty() && location.isNotEmpty()) {
                reportIncident(description, location)
                binding.descriptionEditText.text.clear()
                binding.locationEditText.text.clear()
                Toast.makeText(context, "Incident reported successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun reportIncident(description: String, location: String) {
        val incident = Incident(
            userId = 1, // Assuming user ID is 1 for this example
            description = description,
            location = location,
            timestamp = System.currentTimeMillis()
        )

        GlobalScope.launch(Dispatchers.IO) {
            database.incidentDao().insertIncident(incident)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

