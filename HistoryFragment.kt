package com.example.trainapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainapp.data.AppDatabase
import com.example.trainapp.data.TravelHistory
import com.example.trainapp.databinding.FragmentHistoryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = AppDatabase.getInstance(requireContext())

        binding.historyRecyclerView.layoutManager = LinearLayoutManager(context)

        // Assuming user ID is 1 for this example
        loadTravelHistory(1)
    }

    private fun loadTravelHistory(userId: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val travelHistory = database.travelHistoryDao().getTravelHistoryForUser(userId)
            withContext(Dispatchers.Main) {
                binding.historyRecyclerView.adapter = TravelHistoryAdapter(travelHistory)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class TravelHistoryAdapter(private val travelHistory: List<TravelHistory>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<TravelHistoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val fromToTextView: android.widget.TextView = view.findViewById(R.id.fromToTextView)
        val dateTextView: android.widget.TextView = view.findViewById(R.id.dateTextView)
        val equipmentTextView: android.widget.TextView = view.findViewById(R.id.equipmentTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.travel_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = travelHistory[position]
        holder.fromToTextView.text = "${history.from} to ${history.to}"
        holder.dateTextView.text = history.date
        holder.equipmentTextView.text = if (history.extraEquipment) "Extra equipment needed" else "No extra equipment"
    }

    override fun getItemCount() = travelHistory.size
}

