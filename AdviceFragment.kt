package com.example.trainapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.trainapp.databinding.FragmentAdviceBinding

class AdviceFragment : Fragment() {

    private var _binding: FragmentAdviceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdviceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            val advice = binding.adviceEditText.text.toString()
            if (advice.isNotEmpty()) {
                // Here you would typically send this to your backend
                submitAdvice(advice)
                binding.adviceEditText.text.clear()
                Toast.makeText(context, "Advice submitted successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Please enter your advice", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun submitAdvice(advice: String) {
        // Implement the logic to send the advice to your backend
        // This could involve making an API call using Retrofit or similar
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

