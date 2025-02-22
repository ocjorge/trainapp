package com.example.trainapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainapp.data.Alert
import com.example.trainapp.data.AppDatabase
import com.example.trainapp.databinding.FragmentAlertsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlertsFragment : Fragment() {

    private var _binding: FragmentAlertsBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlertsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = AppDatabase.getInstance(requireContext())

        binding.alertsRecyclerView.layoutManager = LinearLayoutManager(context)

        loadAlerts()
    }

    private fun loadAlerts() {
        GlobalScope.launch(Dispatchers.IO) {
            val alerts = database.alertDao().getAllAlerts()
            withContext(Dispatchers.Main) {
                binding.alertsRecyclerView.adapter = AlertsAdapter(alerts)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class AlertsAdapter(private val alerts: List<Alert>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<AlertsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val titleTextView: android.widget.TextView = view.findViewById(R.id.alertTitleTextView)
        val messageTextView: android.widget.TextView = view.findViewById(R.id.alertMessageTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.alert_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val alert = alerts[position]
        holder.titleTextView.text = alert.title
        holder.messageTextView.text = alert.message
    }

    override fun getItemCount() = alerts.size
}

