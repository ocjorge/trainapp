package com.example.trainapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.trainapp.databinding.FragmentTicketsBinding
import com.example.trainapp.util.QRCodeGenerator
import com.example.trainapp.util.QRCodeScanner
import com.example.trainapp.util.TicketValidator

class TicketsFragment : Fragment() {

    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!
    private lateinit var qrCodeScanner: QRCodeScanner
    private val ticketValidator = TicketValidator()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        qrCodeScanner = QRCodeScanner(requireContext())

        // Assuming you have a ticket object
        val ticket = Ticket(1, 1, "New York", "Boston", "2023-06-01", "")
        
        // Generate QR code
        val qrCodeBitmap = QRCodeGenerator.generateQRCode(ticket.toString(), 200, 200)
        
        // Display QR code
        binding.qrCodeImageView.setImageBitmap(qrCodeBitmap)

        binding.scanButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                qrCodeScanner.scanQRCode(uri) { result ->
                    result?.let {
                        val ticket = parseTicketFromQRCode(it)
                        val isActive = ticketValidator.isTicketActive(ticket.date)
                        binding.ticketStatusTextView.text = if (isActive) "Ticket is active" else "Ticket has expired"
                    } ?: run {
                        binding.ticketStatusTextView.text = "Invalid QR code"
                    }
                }
            }
        }
    }

    private fun parseTicketFromQRCode(qrContent: String): Ticket {
        // Implement parsing logic here
        // For simplicity, we'll assume the QR content is in the format "id,userId,from,to,date"
        val parts = qrContent.split(",")
        return Ticket(parts[0].toInt(), parts[1].toInt(), parts[2], parts[3], parts[4], qrContent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}

