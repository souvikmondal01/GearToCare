package com.geartocare.geartocare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geartocare.geartocare.databinding.ActivityBookingDetailsBinding

class BookingDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}